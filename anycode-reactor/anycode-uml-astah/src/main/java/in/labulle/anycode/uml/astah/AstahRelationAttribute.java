package in.labulle.anycode.uml.astah;

import in.labulle.anycode.uml.Cardinality;
import in.labulle.anycode.uml.IRelationAttribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.change_vision.jude.api.inf.model.IMultiplicityRange;

public class AstahRelationAttribute extends AstahAttribute implements
		IRelationAttribute {
	
	private static final Logger LOG = LoggerFactory.getLogger(AstahRelationAttribute.class);

	public AstahRelationAttribute(
			com.change_vision.jude.api.inf.model.IAttribute astahElt) {
		super(astahElt);
		if(LOG.isDebugEnabled()) {
			LOG.debug("New relation attribute : " + astahElt.getName());
		}
	}

	public Cardinality getCardinality() {
		if (getAstahElement().getMultiplicity() == null) {
			return Cardinality.ZERO_TO_ONE;
		} else {
			int lower = getAstahElement().getMultiplicity()[0].getLower();
			int upper = getAstahElement().getMultiplicity()[0].getUpper();
			if (lower == 0 && upper == IMultiplicityRange.UNLIMITED) {
				return Cardinality.ZERO_TO_MANY;
			} else if (lower == 1 && upper == IMultiplicityRange.UNLIMITED) {
				return Cardinality.ONE_TO_MANY;
			} else if (lower == 0 && upper == 1) {
				return Cardinality.ZERO_TO_ONE;
			} else if (lower == 1 && upper == 1) {
				return Cardinality.ONE_TO_ONE;
			}
			return Cardinality.ZERO_TO_ONE;
		}
	}

	public boolean isRelation() {
		return true;
	}

	public boolean isNavigable() {
		return "Navigable".equals(getAstahElement().getNavigability())
				|| "Unspecified".equals(getAstahElement().getNavigability());
	}

	public boolean isComposition() {
		return getAstahElement().isComposite();
	}

	public boolean isAggregation() {
		return getAstahElement().isAggregate();
	}


	public boolean isManyToOne() {
		return getCardinality().isSingle()
				&& !getOtherSide().getCardinality().isSingle();
	}

	public boolean isOneToOne() {
		return getCardinality().isSingle()
				&& getOtherSide().getCardinality().isSingle();
	}

	public boolean isManyToMany() {
		return !getCardinality().isSingle()
				&& !getOtherSide().getCardinality().isSingle();
	}

	public boolean isOneToMany() {
		return !getCardinality().isSingle()
				&& getOtherSide().getCardinality().isSingle();
	}

	public IRelationAttribute getOtherSide() {
		com.change_vision.jude.api.inf.model.IAttribute[] memberEnds = getAstahElement()
				.getAssociation().getMemberEnds();
		if (!this.getAstahElement().equals(memberEnds[0])) {
			return new AstahRelationAttribute(memberEnds[0]);
		} else {
			return new AstahRelationAttribute(memberEnds[1]);
		}
	}

	public boolean isOwningSide() {
		return (isManyToOne() && isBidirectionalRelation()) || ((isManyToMany() || isOneToOne()) && isBidirectionalRelation() && "Navigable".equals(getAstahElement().getNavigability()));
	}
	
	public boolean isBidirectionalRelation() {
		return isNavigable() && getOtherSide().isNavigable();
	}

}

package in.labulle.anycode.uml.astah;

import com.change_vision.jude.api.inf.model.IMultiplicityRange;

import in.labulle.anycode.uml.Cardinality;
import in.labulle.anycode.uml.IAttribute;

public class AstahAttribute extends
		AstahElement<com.change_vision.jude.api.inf.model.IAttribute> implements
		IAttribute {

	public AstahAttribute(
			com.change_vision.jude.api.inf.model.IAttribute astahElt) {
		super(astahElt);
	}

	public Cardinality getCardinality() {
		if (getAstahElement().getMultiplicity() == null) {
			return Cardinality.ZERO_TO_ONE;
		} else {
			int lower = getAstahElement().getMultiplicity()[0].getLower();
			int upper = getAstahElement().getMultiplicity()[0].getUpper();
			if (lower == IMultiplicityRange.UNLIMITED
					&& upper == IMultiplicityRange.UNLIMITED) {
				return Cardinality.MANY_TO_MANY;
			} else if (lower == 0 && upper == IMultiplicityRange.UNLIMITED) {
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
		return getAstahElement().getAssociation() != null;
	}

	public boolean isNavigable() {
		return "Navigable".equals(getAstahElement().getNavigability()) || "Unspecified".equals(getAstahElement().getNavigability());
	}

	public boolean isComposition() {
		return getAstahElement().isComposite();
	}

	public boolean isAggregation() {
		return getAstahElement().isAggregate();
	}

}

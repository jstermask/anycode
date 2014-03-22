package in.labulle.anycode.uml.astah;

import in.labulle.anycode.repository.astah.ModelException;
import in.labulle.anycode.uml.Cardinality;
import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClassifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AstahAttribute extends
		AstahElement<com.change_vision.jude.api.inf.model.IAttribute> implements
		IAttribute {
	
	private static final Logger LOG = LoggerFactory.getLogger(AstahAttribute.class);

	public AstahAttribute(
			com.change_vision.jude.api.inf.model.IAttribute astahElt) {
		super(astahElt);
		if (AstahAttribute.class.equals(getClass()) &&  astahElt.getAssociation() != null) {
			throw new ModelException(new IllegalArgumentException(
					"This attribute " + astahElt.getName()
							+ " is part of a relation !"));
		} 
		if(LOG.isDebugEnabled()) {
			LOG.debug("New attribute : " + astahElt.getName());
		}
	}

	public Cardinality getCardinality() {
		return Cardinality.ZERO_TO_ONE;
	}

	public boolean isRelation() {
		return false;
	}

	public IClassifier getDataType() {
		return (IClassifier) AstahElement.getElement(getAstahElement());
	}

}

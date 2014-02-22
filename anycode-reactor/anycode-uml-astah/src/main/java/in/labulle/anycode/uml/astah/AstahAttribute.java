package in.labulle.anycode.uml.astah;

import in.labulle.anycode.repository.astah.ModelException;
import in.labulle.anycode.uml.Cardinality;
import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IDataType;

public class AstahAttribute extends
		AstahElement<com.change_vision.jude.api.inf.model.IAttribute> implements
		IAttribute {

	public AstahAttribute(
			com.change_vision.jude.api.inf.model.IAttribute astahElt) {
		super(astahElt);
		if (astahElt.getAssociation() != null) {
			throw new ModelException(new IllegalArgumentException(
					"This attribute " + astahElt.getName()
							+ " is part of a relation !"));
		}
	}

	public Cardinality getCardinality() {
		return Cardinality.ZERO_TO_ONE;
	}

	public boolean isRelation() {
		return false;
	}

	public IDataType getDataType() {
		return new AstahDataType(getAstahElement().getType());
	}

}

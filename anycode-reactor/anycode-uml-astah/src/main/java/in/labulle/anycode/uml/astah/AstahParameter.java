package in.labulle.anycode.uml.astah;

import in.labulle.anycode.uml.IDataType;
import in.labulle.anycode.uml.IParameter;

public class AstahParameter extends AstahElement<com.change_vision.jude.api.inf.model.IParameter> implements IParameter {
	public AstahParameter(
			com.change_vision.jude.api.inf.model.IParameter astahElt) {
		super(astahElt);
	}
	
	public IDataType getDataType() {
		return new AstahDataType(getAstahElement().getType());
	}
	
	

	

}

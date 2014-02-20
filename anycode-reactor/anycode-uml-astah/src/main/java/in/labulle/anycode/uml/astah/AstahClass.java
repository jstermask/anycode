package in.labulle.anycode.uml.astah;

import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClass;

import java.util.List;

public class AstahClass extends AstahElement<com.change_vision.jude.api.inf.model.IClass> implements IClass {

	public AstahClass(com.change_vision.jude.api.inf.model.IClass astahElt) {
		super(astahElt);
	}

	public List<IAttribute> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

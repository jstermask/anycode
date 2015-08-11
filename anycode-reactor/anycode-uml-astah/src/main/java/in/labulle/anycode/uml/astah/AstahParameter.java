package in.labulle.anycode.uml.astah;

import com.change_vision.jude.api.inf.model.IConstraint;
import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IParameter;

import java.util.ArrayList;
import java.util.List;

public class AstahParameter extends AstahElement<com.change_vision.jude.api.inf.model.IParameter> implements IParameter {
	public AstahParameter(
			com.change_vision.jude.api.inf.model.IParameter astahElt) {
		super(astahElt);
	}
	
	public IClassifier getDataType() {
		return (IClassifier) AstahElement.getElement(getAstahElement().getType());
	}

	public boolean isReturnType() {
		return "return".equals(getAstahElement().getDirection());
	}

	public String getTypeModifier() {
		return getAstahElement().getTypeModifier();
	}


	public List<String> getConstraints() {
		List<String> constraints = new ArrayList<String>(5);
		for(IConstraint c : getAstahElement().getConstraints()) {
			constraints.add(c.getName());
		}
		return constraints;
	}
}

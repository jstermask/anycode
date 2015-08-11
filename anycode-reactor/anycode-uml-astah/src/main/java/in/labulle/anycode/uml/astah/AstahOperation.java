package in.labulle.anycode.uml.astah;

import com.change_vision.jude.api.inf.model.IConstraint;
import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IOperation;
import in.labulle.anycode.uml.IParameter;

import java.util.ArrayList;
import java.util.List;

public class AstahOperation extends
		AstahElement<com.change_vision.jude.api.inf.model.IOperation> implements
		IOperation {

	public AstahOperation(
			com.change_vision.jude.api.inf.model.IOperation astahElt) {
		super(astahElt);
	}

	public String getName() {
		return getAstahElement().getName();
	}

	public IClassifier getReturnType() {
		return (IClassifier) AstahElement.getElement(getAstahElement().getReturnType());
	}

	public String getTypeModifier() {
		return getAstahElement().getTypeModifier();
	}

	public List<IParameter> getParameters() {
		List<IParameter> params = new ArrayList<IParameter>();
		for (com.change_vision.jude.api.inf.model.IParameter param : getAstahElement()
				.getParameters()) {
			params.add(new AstahParameter(param));
		}
		return params;
	}
	
	public boolean isAbstract() {
		return getAstahElement().isAbstract();
	}

	public boolean isLeaf() {
		return getAstahElement().isLeaf();
	}

	public boolean isStatic() {
		return getAstahElement().isStatic();
	}

	public List<String> getConstraints() {
		List<String> constraints = new ArrayList<String>(5);
		for(IConstraint c : getAstahElement().getConstraints()) {
			constraints.add(c.getName());
		}
		return constraints;
	}

}

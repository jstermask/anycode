package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IOperation;
import in.labulle.anycode.uml.IParameter;

import java.util.ArrayList;
import java.util.List;

public class AOperation extends AElement implements IOperation {

	private IClassifier returnType;

	private final List<IParameter> parameters = new ArrayList<IParameter>();

	public IClassifier getReturnType() {
		return returnType;
	}

	public void setReturnType(IClassifier returnType) {
		this.returnType = returnType;
	}

	public List<IParameter> getParameters() {
		return parameters;
	}

	public void addParameter(IParameter param) {
		this.parameters.add(param);
	}

	public void removeParameter(IParameter param) {
		this.parameters.remove(param);
	}

}

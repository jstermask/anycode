package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IParameter;

import java.util.ArrayList;
import java.util.List;

public class AParameter extends AElement implements IParameter {
	private IClassifier dataType;
	
	private boolean returnType = false;

	private String typeModifier;

	private final List<String> constraints = new ArrayList<String>();

	public IClassifier getDataType() {
		return dataType;
	}

	public void setDataType(IClassifier dataType) {
		this.dataType = dataType;
	}

	public boolean isReturnType() {
		return returnType;
	}
	
	public void setReturnType(boolean returnType) {
		this.returnType = returnType;
	}

	public String getTypeModifier() {
		return typeModifier;
	}

	public void setTypeModifier(String typeModifier) {
		this.typeModifier = typeModifier;
	}

	public List<String> getConstraints() {
		return constraints;
	}

	public void addConstraint(final String c) {
		this.constraints.add(c);
	}
	public void removeConstraint(final String c) {
		this.constraints.remove(c);
	}
}

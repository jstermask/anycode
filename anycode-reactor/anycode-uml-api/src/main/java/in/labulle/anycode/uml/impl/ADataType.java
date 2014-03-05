package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IDataType;

public class ADataType implements IDataType {
	private IClassifier classifier;
	
	private boolean primitive;

	public boolean isPrimitive() {
		return this.primitive;
	}
	
	public void setPrimitive(boolean primitive) {
		this.primitive = primitive;
	}

	public IClassifier getClassifier() {
		return classifier;
	}
	
	public void setClassifier(IClassifier classifier) {
		this.classifier = classifier;
	}

	public String getName() {
		return this.classifier.getName();
	}

}

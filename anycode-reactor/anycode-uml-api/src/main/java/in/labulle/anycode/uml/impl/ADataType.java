package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.IDataType;

public class ADataType implements IDataType {
	private IClass classifier;
	
	private boolean primitive;

	public boolean isPrimitive() {
		return this.primitive;
	}
	
	public void setPrimitive(boolean primitive) {
		this.primitive = primitive;
	}

	public IClass getClassifier() {
		return classifier;
	}
	
	public void setClassifier(IClass classifier) {
		this.classifier = classifier;
	}

	public String getName() {
		return this.classifier.getName();
	}

}

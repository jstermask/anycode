package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IParameter;

public class AParameter extends AElement implements IParameter {
	private IClassifier dataType;

	public IClassifier getDataType() {
		return dataType;
	}

	public void setDataType(IClassifier dataType) {
		this.dataType = dataType;
	}

}

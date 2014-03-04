package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IDataType;
import in.labulle.anycode.uml.IParameter;

public class AParameter extends AElement implements IParameter {
	private IDataType dataType;

	public IDataType getDataType() {
		return dataType;
	}

	public void setDataType(IDataType dataType) {
		this.dataType = dataType;
	}

}

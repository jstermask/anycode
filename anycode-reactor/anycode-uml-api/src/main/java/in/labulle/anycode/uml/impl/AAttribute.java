package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.Cardinality;
import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IDataType;

public class AAttribute extends AElement implements IAttribute {
	private IDataType dataType;
	
	private Cardinality cardinality = Cardinality.ZERO_TO_ONE;
	
	
	public IDataType getDataType() {
		return dataType;
	}
	
	public void setDataType(IDataType dataType) {
		this.dataType = dataType;
	}

	public Cardinality getCardinality() {
		return cardinality;
	}
	
	public void setCardinality(Cardinality cardinality) {
		this.cardinality = cardinality;
	}
	
	public boolean isRelation() {
		return false;
	}
}

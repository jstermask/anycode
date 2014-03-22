package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.Cardinality;
import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClassifier;

public class AAttribute extends AElement implements IAttribute {
	private IClassifier dataType;
	
	private Cardinality cardinality = Cardinality.ZERO_TO_ONE;
	
	
	public IClassifier getDataType() {
		return dataType;
	}
	
	public void setDataType(IClassifier dataType) {
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

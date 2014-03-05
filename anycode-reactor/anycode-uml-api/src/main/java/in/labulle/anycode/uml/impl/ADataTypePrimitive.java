package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IDataType;

public enum ADataTypePrimitive implements IDataType {
	FLOAT, DOUBLE, STRING, DATE, DATETIME, INTEGER, SHORT, LONG;

	public boolean isPrimitive() {
		return true;
	}

	public IClassifier getClassifier() {
		return null;
	}

	public String getName() {
		return toString().toLowerCase();
	}
}

package in.labulle.anycode.uml;

public interface IDataType {
	public enum Primitive {
		STRING, FLOAT, DOUBLE, INTEGER, DATE, DATETIME;
	}
	
	
	boolean isPrimitive();
	IClassifier getClassifier();
	String getName();
}

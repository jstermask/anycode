package in.labulle.anycode.uml;

public interface IAttribute extends IElement {
	Cardinality getCardinality();
	IDataType getDataType();
}

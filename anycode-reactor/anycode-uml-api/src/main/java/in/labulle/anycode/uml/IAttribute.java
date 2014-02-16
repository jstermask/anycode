package in.labulle.anycode.uml;

public interface IAttribute extends IElement {
	Cardinality getCardinality();
	boolean isRelation();
}

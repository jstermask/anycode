package in.labulle.anycode.uml;

public interface IRelationAttribute extends IAttribute {
	boolean isNavigable();
	boolean isComposition();
	boolean isAggregation();
	boolean isManyToOne();
	boolean isOneToOne();
	boolean isManyToMany();
	boolean isOneToMany();
	IRelationAttribute getOtherSide();
}

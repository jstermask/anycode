package in.labulle.anycode.uml;

/**
 * UML attribute.
 * 
 * @author Jose Carreno
 * 
 */
public interface IAttribute extends IElement {
	/**
	 * Getter for attribute's cardinality.
	 * 
	 * @return cardinality.
	 */
	Cardinality getCardinality();

	/**
	 * Getter for attribute's datatype.
	 * 
	 * @return datatype.
	 */
	IDataType getDataType();

	/**
	 * Checks whether this attribute is part of a relation.
	 * 
	 * @return true if attribute is part of a relation.
	 */
	boolean isRelation();
}

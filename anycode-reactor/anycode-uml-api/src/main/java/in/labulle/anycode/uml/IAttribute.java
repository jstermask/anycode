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
	IClassifier getDataType();

	/**
	 * Checks whether this attribute is part of a relation.
	 * 
	 * @return true if attribute is part of a relation.
	 */
	boolean isRelation();

	/**
	 * Getter for attribute's initial value
	 * @return attribute's initial value
	 */
	String getInitialValue();


	/**
	 * Getter for type modifier
	 * @return type modifier
	 */
	String getTypeModifier();

	/**
	 * Getter for static
	 * @return true if attribute is static
	 */
	boolean isStatic();


	/**
	 * Getter for derived
	 * @return true if attribute is derived
	 */
	boolean isDerived();




}

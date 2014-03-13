package in.labulle.anycode.uml;

/**
 * UML Attribute's cardinality.
 * 
 * @author Jose Carreno
 * 
 */
public enum Cardinality {
	/**
	 * Zero to one.
	 */
	ZERO_TO_ONE,
	/**
	 * One to one.
	 */
	ONE_TO_ONE,
	/**
	 * Zero to many.
	 */
	ZERO_TO_MANY,

	/**
	 * One to many.
	 */
	ONE_TO_MANY;

	/**
	 * Checks whether cardinality's maximum is one.
	 * 
	 * @return true if cardinality is "zero to one" or "one to one".
	 */
	public boolean isSingle() {
		return this.equals(ZERO_TO_ONE) || this.equals(ONE_TO_ONE);
	}

	/**
	 * Checks whether cardinality's minimum is zero
	 * 
	 * @return true if cardinality is "zero to one" or "zero to many".
	 */
	public boolean isOptional() {
		return !this.equals(ONE_TO_MANY) && !this.equals(ONE_TO_ONE);
	}
}

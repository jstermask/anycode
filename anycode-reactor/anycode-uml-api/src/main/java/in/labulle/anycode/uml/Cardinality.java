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
	ZERO_TO_ONE("0..1"),
	/**
	 * One to one.
	 */
	ONE_TO_ONE("1..1"),
	/**
	 * Zero to many.
	 */
	ZERO_TO_MANY("0..*"),

	/**
	 * One to many.
	 */
	ONE_TO_MANY("1..*");

	private String cardStr;

	private Cardinality(final String card) {
		this.cardStr = card;
	}

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

	public static Cardinality fromRange(String lowerRange, String upperRange) {
		String l = "1";
		String u = "1";
		if (lowerRange != null && !"".equals(lowerRange)) {
			l = lowerRange;
		}

		if (upperRange != null && !"".equals(upperRange)) {
			u = upperRange;
		}
		for (Cardinality c : Cardinality.values()) {
			String card = l + ".." + u;
			if(c.cardStr.equals(card)) {
				return c;
			}
		}
		return Cardinality.ONE_TO_ONE;
	}
}

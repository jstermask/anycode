package in.labulle.anycode.uml;

public enum Cardinality {
	ZERO_TO_ONE, ONE_TO_ONE, ZERO_TO_MANY, ONE_TO_MANY, MANY_TO_MANY;
	
	public boolean isSingle() {
		return this.equals(ZERO_TO_ONE) || this.equals(ONE_TO_ONE);
	}
	
	public boolean isOptional() {
		return !this.equals(ONE_TO_MANY) && !this.equals(ONE_TO_ONE);
	}
}

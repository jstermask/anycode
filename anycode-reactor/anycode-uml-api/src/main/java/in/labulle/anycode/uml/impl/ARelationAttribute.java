package in.labulle.anycode.uml.impl;


public class ARelationAttribute extends AAttribute {
	public enum Qualifier {
		COMPOSITION, AGGREGATION;
	}
	
	public enum Navigability {
		NAVIGABLE, NON_NAVIGABLE;
	}
	
	private ARelationAttribute otherSide;
	
	private Qualifier qualifier;
	
	private Navigability navigability;
	

	
	@Override
	public boolean isRelation() {
		return true;
	}
	
	public ARelationAttribute getOtherSide() {
		return otherSide;
	}
	
	public void setOtherSide(ARelationAttribute otherSide) {
		this.otherSide = otherSide;
		if(this.otherSide.getOtherSide() != this) {
			this.otherSide.setOtherSide(this);
		}
	}
	
	@Override
	public boolean isComposition() {
		return Qualifier.COMPOSITION.equals(this.qualifier);
	}
	
	@Override
	public boolean isAggregation() {
		return Qualifier.AGGREGATION.equals(this.qualifier);
	}
	
	@Override
	public boolean isNavigable() {
		return navigability == null || Navigability.NAVIGABLE.equals(this.navigability);
	}
	
	public void setNavigability(Navigability navigability) {
		this.navigability = navigability;
	}
	
	public void setQualifier(Qualifier qualifier) {
		this.qualifier = qualifier;
	}
	
	
}

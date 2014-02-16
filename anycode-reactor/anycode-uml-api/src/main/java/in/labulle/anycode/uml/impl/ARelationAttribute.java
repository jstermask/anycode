package in.labulle.anycode.uml.impl;


public class ARelationAttribute extends AAttribute {
	private ARelationAttribute otherSide;
	
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
}

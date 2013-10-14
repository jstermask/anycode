package in.labulle.anycode.api;

public class UMLAttribute extends UMLElement {
	private UMLAssociation association;
	
	public UMLAssociation getAssociation() {
		return association;
	}
	
	public void setAssociation(UMLAssociation association) {
		this.association = association;
	}
}

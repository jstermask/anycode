package in.labulle.anycode.api;

import java.util.ArrayList;
import java.util.List;

public class UMLPackage extends UMLElement {
	private List<UMLElement> ownedElements = new ArrayList<UMLElement>();
	
	public List<UMLElement> getOwnedElements() {
		return ownedElements;
	}
	
	public void setOwnedElements(List<UMLElement> ownedElements) {
		this.ownedElements = ownedElements;
	}
	
	public void addOwnedElement(UMLElement element) {
		this.ownedElements.add(element);
	}
	
	public void removeElement(UMLElement element) {
		this.ownedElements.remove(element);
	}
}

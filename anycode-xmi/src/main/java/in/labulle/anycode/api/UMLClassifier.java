package in.labulle.anycode.api;

import java.util.ArrayList;
import java.util.List;

public abstract class UMLClassifier extends UMLElement {
	private List<UMLAttribute> attributes = new ArrayList<UMLAttribute>();

	private List<UMLOperation> operations = new ArrayList<UMLOperation>();

	public List<UMLAttribute> getAttributes() {
		return attributes;
	}

	public List<UMLOperation> getOperations() {
		return operations;
	}

	public void setAttributes(List<UMLAttribute> attributes) {
		this.attributes = attributes;
	}

	public void setOperations(List<UMLOperation> operations) {
		this.operations = operations;
	}

}

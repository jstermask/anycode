package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IElement;

public class AElement implements IElement {

	private String name;

	private IElement owner;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IElement getOwner() {
		return owner;
	}

	public void setOwner(IElement owner) {
		this.owner = owner;
	}

	public String getFullyQualifiedName(String separator) {
		StringBuffer buf = new StringBuffer(getName());
		IElement owner = getOwner();
		while (owner != null) {
			buf.insert(0, owner.getName() + separator);
			owner = owner.getOwner();
		}
		return buf.toString();
	}

}

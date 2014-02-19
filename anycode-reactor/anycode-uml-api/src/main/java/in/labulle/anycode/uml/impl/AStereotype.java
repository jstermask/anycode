package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IStereotype;

public class AStereotype implements IStereotype {

	private String name;
	
	public AStereotype(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof IStereotype && getName() != null) {
			return getName().equals(((IStereotype)obj).getName());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return this.name != null ? this.name.hashCode() : "null".hashCode();
	}

}

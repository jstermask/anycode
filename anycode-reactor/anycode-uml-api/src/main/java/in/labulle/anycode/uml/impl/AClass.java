package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClass;

import java.util.ArrayList;
import java.util.List;

public class AClass extends AElement implements IClass {
	private final List<IAttribute> attributes = new ArrayList<IAttribute>();
	
	public List<IAttribute> getAttributes() {
		return attributes;
	}
	
	public void addAttribute(IAttribute att) {
		this.attributes.add(att);
	}
	
	public void removeAttribute(IAttribute att) {
		this.attributes.remove(att);
	}
	
	
}

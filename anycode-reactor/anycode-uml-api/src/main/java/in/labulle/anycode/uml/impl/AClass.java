package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.IOperation;

import java.util.ArrayList;
import java.util.List;

public class AClass extends AElement implements IClass {
	private final List<IAttribute> attributes = new ArrayList<IAttribute>();
	private final List<IOperation> operations = new ArrayList<IOperation>();

	public List<IAttribute> getAttributes() {
		return attributes;
	}

	public void addAttribute(IAttribute att) {
		this.attributes.add(att);
	}

	public void removeAttribute(IAttribute att) {
		this.attributes.remove(att);
	}

	public List<IOperation> getOperations() {
		return operations;
	}

	public void addOperation(IOperation op) {
		this.operations.add(op);
	}

	public void removeOperation(IOperation op) {
		this.operations.remove(op);
	}

}

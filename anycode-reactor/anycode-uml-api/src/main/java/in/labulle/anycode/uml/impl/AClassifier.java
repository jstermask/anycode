package in.labulle.anycode.uml.impl;

import java.util.ArrayList;
import java.util.List;

import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IOperation;

public class AClassifier extends AElement implements IClassifier {
	private final List<IAttribute> attributes = new ArrayList<IAttribute>();
	private final List<IOperation> operations = new ArrayList<IOperation>();
	private final List<? extends IClassifier> generalizations = new ArrayList<IClassifier>();
	private final List<? extends IClassifier> clientDependencies = new ArrayList<IClassifier>();
	private boolean primitive;
	

	public AClassifier() {
		super();
	}
	
	public AClassifier(String name) {
		super(name);
	}
	
	public List<IAttribute> getAttributes() {
		return attributes;
	}

	public List<IOperation> getOperations() {
		return operations;
	}

	public void addAttribute(IAttribute att) {
		this.attributes.add(att);
	}

	public void removeAttribute(IAttribute att) {
		this.attributes.remove(att);
	}

	public void addOperation(IOperation op) {
		this.operations.add(op);
	}

	public void removeOperation(IOperation op) {
		this.operations.remove(op);
	}

	public List<? extends IClassifier> getGeneralizations() {
		return generalizations;
	}
	
	public List<? extends IClassifier> getClientDependencies() {
		return this.clientDependencies;
	}
	
	public void setPrimitive(boolean primitive) {
		this.primitive = primitive;
	}
	
	public boolean isPrimitive() {
		return primitive;
	}
}

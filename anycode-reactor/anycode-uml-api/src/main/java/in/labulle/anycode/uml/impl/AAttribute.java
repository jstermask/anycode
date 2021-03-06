package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.Cardinality;
import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClassifier;

import java.util.ArrayList;
import java.util.List;

public class AAttribute extends AElement implements IAttribute {
	private IClassifier dataType;

	private Cardinality cardinality = Cardinality.ZERO_TO_ONE;

	private String initialValue;

	private String typeModifier;

	private boolean isStatic;

	private boolean derived;

	private final List<String> constraints = new ArrayList<String>();

	public AAttribute() {
		super();
	}

	public AAttribute(String name) {
		super(name);
	}

	public IClassifier getDataType() {
		return dataType;
	}

	public void setDataType(IClassifier dataType) {
		this.dataType = dataType;
	}

	public Cardinality getCardinality() {
		return cardinality;
	}

	public void setCardinality(Cardinality cardinality) {
		this.cardinality = cardinality;
	}

	public boolean isRelation() {
		return false;
	}

	public String getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public String getTypeModifier() {
		return typeModifier;
	}

	public void setTypeModifier(String typeModifier) {
		this.typeModifier = typeModifier;
	}

	public boolean isDerived() {
		return derived;
	}

	public void setDerived(boolean derived) {
		this.derived = derived;
	}

	public List<String> getConstraints() {
		return constraints;
	}

	public void addConstraint(final String c) {
		this.constraints.add(c);
	}
	public void removeConstraint(final String c) {
		this.constraints.remove(c);
	}
}

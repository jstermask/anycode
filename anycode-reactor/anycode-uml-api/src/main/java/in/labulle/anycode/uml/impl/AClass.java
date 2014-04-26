package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.IInterface;

import java.util.ArrayList;
import java.util.List;

public class AClass extends AClassifier implements IClass {
	private final List<IInterface> realizations = new ArrayList<IInterface>();

	public AClass() {
		super();
	}

	public AClass(String name) {
		super(name);
	}

	@SuppressWarnings("unchecked")
	public List<IClass> getGeneralizations() {
		return (List<IClass>) super.getGeneralizations();
	}

	public List<IInterface> getRealizations() {
		return realizations;
	}

}

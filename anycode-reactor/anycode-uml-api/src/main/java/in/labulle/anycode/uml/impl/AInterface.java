package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IInterface;

import java.util.List;

public class AInterface extends AClassifier implements IInterface {

	@SuppressWarnings("unchecked")
	public List<IInterface> getGeneralizations() {
		return (List<IInterface>)super.getGeneralizations();
	}
}

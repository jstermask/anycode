package in.labulle.anycode.uml;

import in.labulle.anycode.uml.impl.AElement;

import java.util.ArrayList;
import java.util.List;

public class AInterface extends AElement implements IInterface {
	private final List<IOperation> operations = new ArrayList<IOperation>();
	private final List<IInterface> generalizations = new ArrayList<IInterface>();

	public List<IOperation> getOperations() {
		return operations;
	}

	public List<IInterface> getGeneralizations() {
		return generalizations;
	}
}

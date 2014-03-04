package in.labulle.anycode.uml;

import java.util.List;

public interface IClass extends IElement {
	List<IAttribute> getAttributes();
	List<IOperation> getOperations();
}

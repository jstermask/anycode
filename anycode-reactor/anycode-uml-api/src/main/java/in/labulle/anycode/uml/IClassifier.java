package in.labulle.anycode.uml;

import java.util.List;

public interface IClassifier extends IElement {
	List<IAttribute> getAttributes();
	List<IOperation> getOperations();
	List<? extends IClassifier> getGeneralizations();
}

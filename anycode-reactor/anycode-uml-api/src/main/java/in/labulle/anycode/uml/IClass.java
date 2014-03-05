package in.labulle.anycode.uml;

import java.util.List;

public interface IClass extends IClassifier {
	List<IAttribute> getAttributes();
	List<IOperation> getOperations();
	List<IClass> getGeneralizations();
	List<IInterface> getRealizations();
}

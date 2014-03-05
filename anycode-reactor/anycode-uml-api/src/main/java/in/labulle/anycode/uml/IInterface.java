package in.labulle.anycode.uml;

import java.util.List;


public interface IInterface extends IClassifier {
	List<IOperation> getOperations();
	List<IInterface> getGeneralizations();
}

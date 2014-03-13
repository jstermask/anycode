package in.labulle.anycode.uml;

import java.util.List;

/**
 * UML Classifier. It can be either a class or an interface.
 * @author Jose Carreno
 *
 */
public interface IClassifier extends IElement {
	List<IAttribute> getAttributes();
	List<IOperation> getOperations();
	List<? extends IClassifier> getGeneralizations();
	List<? extends IClassifier> getClientDependencies();
}

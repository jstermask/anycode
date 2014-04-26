package in.labulle.anycode.uml;

import java.util.List;

/**
 * UML Classifier. It can be either a {@link IClass} or an {@link IInterface}.
 * 
 * @author Jose Carreno
 * 
 */
public interface IClassifier extends IElement {
	/**
	 * Gets classifier attributes. Interface can have static ones.
	 * 
	 * @return classifier's attributes. Can be empty but never null.
	 */
	List<IAttribute> getAttributes();

	/**
	 * Gets classifier operations.
	 * 
	 * @return classifier's operations. Can be empty but never null.
	 */
	List<IOperation> getOperations();

	/**
	 * 
	 * @return a list of generalizations that can be either a list of {@link IInterface} if current classifier is an {@link IInterface} itself. Or a list of
	 *         {@link IClass} if current classifier is an {@link IClass} too.
	 */
	List<? extends IClassifier> getGeneralizations();

	List<? extends IClassifier> getClientDependencies();

	boolean isPrimitive();
}

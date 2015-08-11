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
	 * Gets a list of generalizations that can be either a list of {@link IInterface} if current classifier is an {@link IInterface} itself. Or a list of
	 *         {@link IClass} if current classifier is an {@link IClass} too.
	 * @return a list of generalizations that can be either a list of {@link IInterface} if current classifier is an {@link IInterface} itself. Or a list of
	 *         {@link IClass} if current classifier is an {@link IClass} too. Can be empty but never null.
	 */
	List<? extends IClassifier> getGeneralizations();

	/**
	 * Gets all the classes or interfaces that the current class depends on.
	 * @return List of all the classes or interfaces that the current class depends on. Can be empty but never null.
	 */
	List<IClassifier> getClientDependencies();

	/**
	 * Checks whether this classifier is primitive (int, double, float, string...)
	 * @return true if classifier is primitive type
	 */
	boolean isPrimitive();

	/**
	 *
	 * @return true if classifier is leaf
	 */
	boolean isLeaf();

	/**
	 *
	 * @return true if classifier is active
	 */
	boolean isActive();

	/**
	 * getter for constraints
	 * @return list of constraints
	 */
	List<String> getConstraints();
}

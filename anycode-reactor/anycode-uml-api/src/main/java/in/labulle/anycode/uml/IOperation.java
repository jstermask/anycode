package in.labulle.anycode.uml;

import java.util.List;

/**
 * UML Operation. An operation is used in {@link IClassifier}.
 */
public interface IOperation extends IElement {
	/**
	 * Classifier's return type.
	 * 
	 * @return classifier. Cannot be null.
	 */
	IClassifier getReturnType();

	/**
	 * Getter for type modifier
	 * @return type modifier
	 */
	String getTypeModifier();

	/**
	 * Lists operation's parameters.
	 * 
	 * @return List of parameters. Can be empty but never null.
	 */
	List<IParameter> getParameters();

	/**
	 * Checks whether operation is abstract.
	 * 
	 * @return true if operation is abstract.
	 */
	boolean isAbstract();

	/**
	 *
	 * @return true if method is leaf
	 */
	boolean isLeaf();

	/**
	 * Getter for static
	 * @return true if attribute is static
	 */
	boolean isStatic();

	/**
	 * getter for constraints
	 * @return list of constraints
	 */
	List<String> getConstraints();


}

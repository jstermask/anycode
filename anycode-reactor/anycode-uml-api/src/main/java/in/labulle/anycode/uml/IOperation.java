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
	 * Lists operation's parameters.
	 * 
	 * @return List of parameters. Can be empty but never null.
	 */
	List<IParameter> getParameters();
}

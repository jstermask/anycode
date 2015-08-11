package in.labulle.anycode.uml;

import java.util.List;

/**
 * UML parameter for operations.
 * 
 * @author Jose Carreno
 * 
 */
public interface IParameter extends IElement {

	/**
	 * Parameter's type.
	 * 
	 * @return classifier. Cannot be null.
	 */
	IClassifier getDataType();
	
	/**
	 * 
	 * @return true if this parameter is a return type parameter
	 */
	boolean isReturnType();

	/**
	 * Getter for type modifier
	 * @return type modifier
	 */
	String getTypeModifier();

	/**
	 * getter for constraints
	 * @return list of constraints
	 */
	List<String> getConstraints();
}

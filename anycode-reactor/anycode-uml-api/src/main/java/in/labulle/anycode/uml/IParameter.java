package in.labulle.anycode.uml;

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

}

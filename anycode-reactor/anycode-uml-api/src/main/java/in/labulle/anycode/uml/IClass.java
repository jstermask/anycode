package in.labulle.anycode.uml;

import java.util.List;

/**
 * UML Class.
 * 
 * @author Jose Carreno
 * 
 */
public interface IClass extends IClassifier {
	/**
	 * Gets interfaces that this class implements.
	 * 
	 * @return interfaces that this class implements. It might be an empty list but it will never be null.
	 */
	List<IInterface> getRealizations();

	/**
	 * Gets classes that this class extends (multi-inheritance if possible in UML).
	 * 
	 * @return classes that this class extends. It might be an empty list but it will never be null.
	 */
	List<IClass> getGeneralizations();
}

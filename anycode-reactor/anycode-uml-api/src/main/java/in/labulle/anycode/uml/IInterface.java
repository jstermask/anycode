package in.labulle.anycode.uml;

import java.util.List;

/**
 * UML Interface.
 * 
 * @author Jose Carreno
 * 
 */
public interface IInterface extends IClassifier {
	/**
	 * Gets the list of interfaces that this interface extends. Interfaces support inheritance with other {@link IInterface}.
	 * 
	 * @return list of interfaces that this interface extends. Can be empty but never null.
	 */
	List<IInterface> getGeneralizations();
}

package in.labulle.anycode.uml;

import java.util.List;
/**
 * UML Class.
 * @author Jose Carreno
 *
 */
public interface IClass extends IClassifier {
	List<IInterface> getRealizations();
	List<IClass> getGeneralizations();
}

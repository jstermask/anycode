package in.labulle.anycode.uml;

import java.util.List;

public interface IClass extends IClassifier {
	List<IInterface> getRealizations();
	List<IClass> getGeneralizations();
}

package in.labulle.anycode.uml;

import java.util.List;

public interface IInterface extends IClassifier {
	List<IInterface> getGeneralizations();
}

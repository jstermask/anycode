package in.labulle.anycode.uml;

import java.util.List;

public interface IOperation extends IElement {
	IClassifier getReturnType();
	List<IParameter> getParameters();
}

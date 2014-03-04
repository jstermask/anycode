package in.labulle.anycode.uml;

import java.util.List;

public interface IOperation extends IElement {
	IClass getReturnType();
	List<IParameter> getParameters();
}

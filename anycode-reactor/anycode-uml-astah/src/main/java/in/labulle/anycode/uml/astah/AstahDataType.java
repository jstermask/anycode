package in.labulle.anycode.uml.astah;
import in.labulle.anycode.uml.IDataType;

import com.change_vision.jude.api.inf.model.IClass;

public class AstahDataType extends AstahElement<IClass> implements IDataType {

	
	public AstahDataType(IClass astahClass) {
		super(astahClass);
	}

	public boolean isPrimitive() {
		return getAstahElement() == null;
	}

	public in.labulle.anycode.uml.IClass getClassifier() {
		return new AstahClass(getAstahElement());
	}

	public String getName() {
		return getAstahElement().getName();
	}

}

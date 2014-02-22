package in.labulle.anycode.uml.astah;
import com.change_vision.jude.api.inf.model.IClass;

import in.labulle.anycode.uml.IDataType;
import in.labulle.anycode.uml.astah.utils.ModelUtils;

public class AstahDataType extends AstahElement<IClass> implements IDataType {

	
	public AstahDataType(IClass astahClass) {
		super(astahClass);
	}

	public boolean isPrimitive() {
		return ModelUtils.isJREClass(getAstahElement().getFullName("."));
	}

	public in.labulle.anycode.uml.IClass getClassifier() {
		return new AstahClass(getAstahElement());
	}

	public String getName() {
		return getAstahElement().getName();
	}

}

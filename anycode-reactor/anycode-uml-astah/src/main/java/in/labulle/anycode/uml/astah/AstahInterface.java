package in.labulle.anycode.uml.astah;

import in.labulle.anycode.uml.IInterface;

import java.util.List;

import com.change_vision.jude.api.inf.model.IClass;

public class AstahInterface extends AstahClassifier implements IInterface {

	public AstahInterface(IClass astahElt) {
		super(astahElt);
	}


	@SuppressWarnings("unchecked")
	public List<IInterface> getGeneralizations() {
		return (List<IInterface>) super.getGeneralizations();
	}

	

}

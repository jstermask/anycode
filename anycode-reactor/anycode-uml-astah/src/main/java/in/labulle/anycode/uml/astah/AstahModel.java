package in.labulle.anycode.uml.astah;

import java.util.List;

import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.IModel;

public class AstahModel implements IModel {
	private final com.change_vision.jude.api.inf.model.IModel astahModel;
	
	
	public AstahModel(final com.change_vision.jude.api.inf.model.IModel astah) {
		this.astahModel = astah;
	}

	public List<IClass> getAllClasses() {
		// TODO Auto-generated method stub
		return null;
	}

}

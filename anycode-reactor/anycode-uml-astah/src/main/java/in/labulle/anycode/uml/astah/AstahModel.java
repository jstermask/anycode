package in.labulle.anycode.uml.astah;

import java.util.ArrayList;
import java.util.List;

import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.uml.astah.utils.ModelUtils;

public class AstahModel implements IModel {
	private final com.change_vision.jude.api.inf.model.IModel astahModel;
	
	
	public AstahModel(final com.change_vision.jude.api.inf.model.IModel astah) {
		this.astahModel = astah;
	}

	public List<IClass> getAllClasses() {
		List<com.change_vision.jude.api.inf.model.IClass> astahClasses = ModelUtils.getAllClasses(astahModel);
		List<IClass> classes = new ArrayList<IClass>();
		for(com.change_vision.jude.api.inf.model.IClass astahCl : astahClasses) {
			classes.add(new AstahClass(astahCl));
		}
		return classes;
	}

}

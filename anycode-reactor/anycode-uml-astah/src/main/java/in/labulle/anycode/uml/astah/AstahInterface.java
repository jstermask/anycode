package in.labulle.anycode.uml.astah;

import in.labulle.anycode.uml.IInterface;
import in.labulle.anycode.uml.IOperation;

import java.util.ArrayList;
import java.util.List;

import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.IGeneralization;

public class AstahInterface extends AstahElement<IClass> implements IInterface {

	public AstahInterface(IClass astahElt) {
		super(astahElt);
	}

	public List<IOperation> getOperations() {
		List<IOperation> ops = new ArrayList<IOperation>();
		for(com.change_vision.jude.api.inf.model.IOperation op : getAstahElement().getOperations()) {
			ops.add(new AstahOperation(op));
		}
		return ops;
	}

	public List<IInterface> getGeneralizations() {
		List<IInterface> gens = new ArrayList<IInterface>();
		for(IGeneralization gen : getAstahElement().getGeneralizations()) {
			gens.add(new AstahInterface(gen.getSubType()));
		}
		return gens;
	}

	

}

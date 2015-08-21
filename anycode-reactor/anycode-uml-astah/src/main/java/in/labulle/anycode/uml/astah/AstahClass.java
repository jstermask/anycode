package in.labulle.anycode.uml.astah;

import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.IInterface;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.change_vision.jude.api.inf.model.IRealization;

public class AstahClass extends
		AstahClassifier implements
		IClass {
	private static final Logger LOG = LoggerFactory.getLogger(AstahClass.class);

	public AstahClass(com.change_vision.jude.api.inf.model.IClass astahElt) {
		super(astahElt);
	}



	@SuppressWarnings("unchecked")
	public List<IClass> getGeneralizations() {
		return (List<IClass>)super.getGeneralizations();
	}

	public List<IInterface> getRealizations() {
		List<IInterface> gens = new ArrayList<IInterface>();
		for(IRealization gen : getAstahElement().getClientRealizations()) {
			gens.add(new AstahInterface((com.change_vision.jude.api.inf.model.IClass)gen.getSupplier()));
		}
		return gens;
	}



	public boolean isAbstract() {
		return getAstahElement().isAbstract();
	}



}

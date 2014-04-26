package in.labulle.anycode.uml.astah;

import java.util.ArrayList;
import java.util.List;

import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.INamedElement;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IPackage;

public class AstahPackage extends
		AstahElement<com.change_vision.jude.api.inf.model.IPackage> implements
		IPackage {

	public AstahPackage(com.change_vision.jude.api.inf.model.IPackage astahElt) {
		super(astahElt);
	}

	public List<IPackage> getSubPackages() {
		List<IPackage> subpackages = new ArrayList<IPackage>();
		for(INamedElement elt : getAstahElement().getOwnedElements()) {
			if(elt instanceof com.change_vision.jude.api.inf.model.IPackage) {
				subpackages.add((IPackage)AstahElement.getElement(elt));
			}
		}
		return subpackages;
	}

	public void addSubPackage(IPackage pack) {
		
	}

	public List<IClassifier> getClassifiers() {
		List<IClassifier> classifiers = new ArrayList<IClassifier>();
		for(INamedElement elt : getAstahElement().getOwnedElements()) {
			if(elt instanceof IClass) {
				classifiers.add((IClassifier)AstahElement.getElement(elt));
			}
		}
		return classifiers;
	}

	public void addClassifier(IClassifier cl) {
		
	}

}

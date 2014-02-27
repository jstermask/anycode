package in.labulle.anycode.uml.astah;

import in.labulle.anycode.uml.IElement;
import in.labulle.anycode.uml.IStereotype;
import in.labulle.anycode.uml.Visibility;
import in.labulle.anycode.uml.impl.AStereotype;

import java.util.HashSet;
import java.util.Set;

import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;

public abstract class AstahElement<T extends com.change_vision.jude.api.inf.model.IElement> implements IElement {
	private T astahElement;

	public AstahElement(final T astahElt) {
		this.astahElement = astahElt;
	}

	public static IElement getElement(
			com.change_vision.jude.api.inf.model.IElement e) {
		if (e instanceof IPackage) {
			return new AstahPackage(e);
		} else if (e instanceof IClass) {
			return new AstahClass((IClass) e);
		}
		return null;
	}

	public String getName() {
		if (astahElement instanceof INamedElement) {
			return ((INamedElement) astahElement).getName();
		} else {
			return null;
		}
	}

	public IElement getOwner() {
		return getElement(astahElement.getOwner());
	}

	public String getFullyQualifiedName(String separator) {
		if (astahElement instanceof INamedElement) {
			return ((INamedElement) astahElement).getFullName(separator);
		} else {
			return null;
		}
	}

	public Set<IStereotype> getStereotypes() {
		Set<IStereotype> st = new HashSet<IStereotype>();
		for(String stereotype : astahElement.getStereotypes()) {
			st.add(new AStereotype(stereotype));
		}
		return st;
	}
	
	
	public boolean hasStereotype(final String stereotypeName) {
		return astahElement.hasStereotype(stereotypeName);		
	}
	


	public Visibility getVisibility() {
		if (astahElement instanceof INamedElement) {
			INamedElement elt = (INamedElement)astahElement;
			if(elt.isPublicVisibility()) {
				return Visibility.PUBLIC;
			} else if (elt.isPackageVisibility()) {
				return Visibility.PACKAGE;
			} else if (elt.isProtectedVisibility()) {
				return Visibility.PROTECTED;
			} else if (elt.isPrivateVisibility()) {
				return Visibility.PRIVATE;
			}
		}
		return Visibility.PROTECTED;
	}
	
	protected T getAstahElement() {
		return astahElement;
	}
}

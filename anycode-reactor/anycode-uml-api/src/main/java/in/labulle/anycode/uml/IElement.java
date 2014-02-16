package in.labulle.anycode.uml;

import java.util.Set;

public interface IElement {
	String getName();
	IElement getOwner();
	String getFullyQualifiedName(String separator);
	Set<IStereotype> getStereotypes();
	Visibility getVisibility();
}

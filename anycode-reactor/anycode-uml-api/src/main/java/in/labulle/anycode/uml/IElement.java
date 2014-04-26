package in.labulle.anycode.uml;

import java.util.Set;

public interface IElement {
	String getName();

	String getDocumentation();

	IElement getOwner();

	String getFullyQualifiedName(String separator);

	Set<IStereotype> getStereotypes();

	Visibility getVisibility();

	boolean hasStereotype(String stereotype);

	String getModifier();
}

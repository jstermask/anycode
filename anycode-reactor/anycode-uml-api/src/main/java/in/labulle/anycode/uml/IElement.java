package in.labulle.anycode.uml;

public interface IElement {
	String getName();
	IElement getOwner();
	String getFullyQualifiedName(String separator);
}

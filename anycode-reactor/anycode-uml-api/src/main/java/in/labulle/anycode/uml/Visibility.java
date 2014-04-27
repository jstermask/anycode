package in.labulle.anycode.uml;

/**
 * Element's visibility.
 * 
 * @author Jose Carreno
 * 
 */
public enum Visibility {
	/**
	 * private. Only visible to this element
	 */
	PRIVATE,
	/**
	 * protected. Only visible to classifier hierarchy and classifiers from the same package.
	 */
	PROTECTED,
	/**
	 * public. Visible to any element.
	 */
	PUBLIC,
	/**
	 * package. Visible to the elements of the package.
	 */
	PACKAGE;

}

package in.labulle.anycode.uml;


import java.util.Set;

/**
 * UML Element. This class is parent of almost all uml interfaces.
 * 
 * @author Jose Carreno
 * 
 */
public interface IElement {
	/**
	 * Element's name.
	 * 
	 * @return name.
	 */
	String getName();

	/**
	 * Element's definition or documentation.
	 * 
	 * @return documentation.
	 */
	String getDefinition();

	/**
	 * Element's owner. This can be null if element has no owner (root package for instance)
	 * 
	 * @return element's owner or null if there is no owner for this element.
	 */
	IElement getOwner();

	/**
	 * Calculates element's path, i.e.: it's name prefixed by a separated list of its parents name, ordered from the root to the leaf. (e.g. : separator = '.'
	 * => org.test.core.MyClass, or separator = '/' => org/test/core/MyClass
	 * 
	 * @param separator
	 *            used to separate names inside the path
	 * @return element's path
	 */
	String getFullyQualifiedName(String separator);

	/**
	 * Lists element's stereotypes.
	 * 
	 * @return List of stereotypes, can be empty but never null.
	 */
	Set<IStereotype> getStereotypes();

	/**
	 * Gets element's visibility
	 * 
	 * @return visibility. It must never be null.
	 */
	Visibility getVisibility();

	/**
	 * Checks whether the element has this stereotype, identifier by its name. This is case sensitive, i.e. : "Boundary" <> "boundary" <> "BOUNDARY" are
	 * considered as 3 distinct sterotypes.
	 * 
	 * @param stereotype
	 *            stereotype name.
	 * @return true if this element has this stereotype.
	 */
	boolean hasStereotype(String stereotype);

	/**
	 * Element's modifier. This is a free to use string that should be used to give more details about the type. In java, modifier can be used to give more
	 * information about collections and generics for type safety.
	 * 
	 * @return element's modifier.
	 */
	String getModifier();

	/**
	 * Getter for attribute's readonly
	 * @return true if attribute is readonly
	 */
	boolean isReadOnly();


}

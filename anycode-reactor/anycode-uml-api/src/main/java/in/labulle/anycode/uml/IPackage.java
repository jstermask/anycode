package in.labulle.anycode.uml;

import java.util.List;

/**
 * UML Package.
 * 
 * @author Jose Carreno
 * 
 */
public interface IPackage extends IElement {
	/**
	 * Lists the packages that this package owns directly.
	 * 
	 * @return list of packages. Can be empty but never null.
	 */
	List<IPackage> getSubPackages();

	/**
	 * Adds a package as a child of the current package.
	 * 
	 * @param pack
	 *            package to add. Cannot be null as method is not supposed to be null safe.
	 */
	void addSubPackage(IPackage pack);

	/**
	 * Lists the classes or interfaes that this package owns directly.
	 * 
	 * @return list of classifiers. Can be empty but never null.
	 */
	List<IClassifier> getClassifiers();

	/**
	 * Adds a classifier as a child of the current package.
	 * 
	 * @param cl
	 *            classifier to add. Cannot be null as method is not supposed to be null safe.
	 */
	void addClassifier(IClassifier cl);
	
	/**
	 * Finds a classifier based on the parameter fullyQualified name and the given separator.
	 * @param fullyQualifiedName classifier's fully qualified name.
	 * @param separator separator.
	 * @return a classifier or null if not classifier matches the given parameters.
	 */
	IClassifier findClassifierByFullyQualifiedName(String fullyQualifiedName, String separator);
}

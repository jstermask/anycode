package in.labulle.anycode.uml;

import java.util.List;

/**
 * UML model.
 * 
 * @author Jose Carreno
 * 
 */
public interface IModel {
	/**
	 * Gets all the classifiers (interfaces of classes) in that model, regardless of their package.
	 * 
	 * @return all the classifiers (interfaces of classes) in that model, regardless of their package.
	 */
	List<IClassifier> getAllClasses();

	/**
	 * Get root packages of the model. Only direct children of the UML model are returned.
	 * 
	 * @return root packages of the model.
	 */
	List<IPackage> getPackages();
	
	/**
	 * Get root classes of the model. Only direct children of the UML model are returned.
	 * 
	 * @return root classes of the model.
	 */
	List<IClassifier> getClassifiers();
	
	/**
	 * Finds a classifier based on the parameter fullyQualified name and the given separator.
	 * @param fullyQualifiedName classifier's fully qualified name.
	 * @param separator separator.
	 * @return a classifier or null if not classifier matches the given parameters.
	 */
	IClassifier findClassifierByFullyQualifiedName(String fullyQualifiedName, String separator);
}

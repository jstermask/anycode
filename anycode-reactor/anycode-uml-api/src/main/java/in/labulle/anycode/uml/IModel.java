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
}

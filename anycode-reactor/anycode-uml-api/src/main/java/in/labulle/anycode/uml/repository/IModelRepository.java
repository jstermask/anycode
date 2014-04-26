package in.labulle.anycode.uml.repository;

import in.labulle.anycode.uml.IModel;

/**
 * Astah Project accessor encapsulation.
 * 
 * @author José Carreno
 * 
 */
public interface IModelRepository {

	/**
	 * 
	 * @return the whole model
	 */
	IModel getModelInstance();

	/**
	 * @return model file's path.
	 */
	String getModelFilePath();
}

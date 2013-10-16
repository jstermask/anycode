/**
 * 
 */
package in.labulle.anycode.astah.plugin.model;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.model.IModel;

/**
 * @author KALAO
 * 
 */
public class ModelRepositoryImpl implements IModelRepository {
	@Override
	public IModel getModelInstance() {
		try {
			return AstahAPI.getAstahAPI().getProjectAccessor().getProject();
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}
}

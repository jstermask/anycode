/**
 * 
 */
package in.labulle.anycode.repository.astah;

import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.uml.astah.AstahModel;
import in.labulle.anycode.uml.repository.IModelRepository;

import com.change_vision.jude.api.inf.AstahAPI;


/**
 * @author KALAO
 * 
 */
public class ModelRepositoryImpl implements IModelRepository {
    

    public IModel getModelInstance() {
        try {
            com.change_vision.jude.api.inf.model.IModel model = AstahAPI.getAstahAPI().getProjectAccessor().getProject();
            return new AstahModel(model);
        } catch (Exception e) {
            throw new ModelException(e);
        }
    }

 
    public String getModelFilePath() {
        try {
            return AstahAPI.getAstahAPI().getProjectAccessor().getProjectPath();
        } catch (Exception e) {
            return null;
        }
    }
}

/**
 * 
 */
package in.labulle.anycode.astah.plugin.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.model.IModel;

/**
 * @author KALAO
 * 
 */
public class ModelRepositoryImpl implements IModelRepository {
    private static final Logger LOG = LoggerFactory.getLogger(ModelRepositoryImpl.class);

    @Override
    public IModel getModelInstance() {
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("getModelInstance()");
            }
            IModel model = AstahAPI.getAstahAPI().getProjectAccessor().getProject();
            if (LOG.isErrorEnabled()) {
                LOG.error("getModelInstance() is " + model);
            }
            return model;
        } catch (Exception e) {
            if (LOG.isErrorEnabled()) {
                LOG.error("getModelInstance() failed to load model", e);
            }
            throw new ModelException(e);
        }
    }

    @Override
    public String getModelFilePath() {
        try {
            return AstahAPI.getAstahAPI().getProjectAccessor().getProjectPath();
        } catch (Exception e) {
            return null;
        }
    }
}

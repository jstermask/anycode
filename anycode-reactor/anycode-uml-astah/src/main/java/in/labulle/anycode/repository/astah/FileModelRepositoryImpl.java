package in.labulle.anycode.repository.astah;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.exception.ProjectLockedException;
import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.uml.astah.AstahModel;
import in.labulle.anycode.uml.repository.IModelRepository;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by JJB on 8/12/15.
 */
public class FileModelRepositoryImpl implements IModelRepository {
    private String projectPath;

    public FileModelRepositoryImpl(final String projectPath) {
        this.projectPath = projectPath;
    }

    public IModel getModelInstance() {
        AstahAPI api = null;

        try {
            api = AstahAPI.getAstahAPI();
            api.getProjectAccessor().open(this.projectPath);
            if(api.getProjectAccessor().hasProject()) {
                com.change_vision.jude.api.inf.model.IModel model = api.getProjectAccessor().getProject();
                return new AstahModel(model);
            } else {
                throw new RuntimeException("Unable to load project " + this.projectPath);
            }
        }  catch(ProjectLockedException e) {
            api.getProjectAccessor().close();
            throw new RuntimeException(e);
        }  catch(Exception ee) {
            throw new RuntimeException(ee);
        }

    }

    public String getModelFilePath() {
        return this.projectPath;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }
}

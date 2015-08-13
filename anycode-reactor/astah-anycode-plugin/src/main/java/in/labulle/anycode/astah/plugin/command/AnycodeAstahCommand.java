package in.labulle.anycode.astah.plugin.command;

import in.labulle.anycode.astah.plugin.generator.control.CodeGenerationController;
import in.labulle.anycode.astah.plugin.generator.control.ICodeGenerationContext;
import in.labulle.anycode.astah.plugin.generator.control.impl.CodeGenerationContext;
import in.labulle.anycode.engine.groovy.repository.TemplateRepositoryFactoryImpl;
import in.labulle.anycode.engine.log.ICodeGenerationLog;
import in.labulle.anycode.engine.log.impl.CodeGenerationLog;
import in.labulle.anycode.engine.repository.ITemplateRepositoryFactory;
import in.labulle.anycode.engine.service.ICodeGenerationService;
import in.labulle.anycode.engine.service.impl.ClassCodeGenerationServiceImpl;
import in.labulle.anycode.repository.astah.FileModelRepositoryImpl;
import in.labulle.anycode.uml.repository.IModelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by JJB on 8/12/15.
 */
public class AnycodeAstahCommand {
    private static final Logger LOG = LoggerFactory.getLogger(AnycodeAstahCommand.class);

    private final String projectPath;
    private final String templatePath;
    private final String targetDir;

    private CodeGenerationController controller;

    public AnycodeAstahCommand(final String projectPath, final String templatePath, final String targetDir) {
        this.projectPath = projectPath;
        this.templatePath = templatePath;
        this.targetDir = targetDir;
        this.controller = new CodeGenerationController(getService());
        this.controller.register(getContext());
    }

    public void execute() {
        this.controller.generateCode();
    }

    public static void main(final String[] args) {
        AnycodeAstahCommand c = new AnycodeAstahCommand(args[0], args[1], args[2]);
        if(LOG.isInfoEnabled()) {
            LOG.info(c.toString());
        }
        c.execute();
    }


    private CodeGenerationController getController() {
        return new CodeGenerationController(getService());
    }

    private ICodeGenerationService getService() {
        return new ClassCodeGenerationServiceImpl(getModelRepository(), getTemplateRepositoryFactory());
    }

    private IModelRepository getModelRepository() {
        return new FileModelRepositoryImpl(this.projectPath);
    }

    private ITemplateRepositoryFactory getTemplateRepositoryFactory() {
        return new TemplateRepositoryFactoryImpl();
    }

    private ICodeGenerationContext getContext() {
        CodeGenerationContext c = new CodeGenerationContext();
        c.setLog(getLog());
        c.setDefaultBrowsePath(this.projectPath);
        c.setOutputPath(this.targetDir);
        c.setTemplatePath(this.templatePath);
        return c;
    }

    private ICodeGenerationLog getLog() {
        return new CodeGenerationLog();
    }

    public String toString() {
        return "any<code/> generation command\n" +
                "Project Path : " + this.projectPath + "\n" +
                "Template Path : " + this.templatePath + "\n" +
                "Target directory : " + this.targetDir;
    }
}

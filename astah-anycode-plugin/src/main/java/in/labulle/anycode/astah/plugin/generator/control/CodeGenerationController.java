package in.labulle.anycode.astah.plugin.generator.control;

import in.labulle.anycode.astah.plugin.exception.AnycodeRuntimeException;
import in.labulle.anycode.astah.plugin.generator.service.ICodeGenerationService;
import in.labulle.anycode.astah.plugin.generator.ui.CodeGenerationTask;
import in.labulle.anycode.astah.plugin.mvc.IController;
import in.labulle.anycode.astah.plugin.template.ICodeGenerationArtifact;
import in.labulle.anycode.astah.plugin.template.IMacro;
import in.labulle.anycode.astah.plugin.template.ITemplate;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeGenerationController implements IController<ICodeGenerationContext> {
    private static final Logger LOG = LoggerFactory.getLogger(CodeGenerationController.class);

    private ICodeGenerationContext context;

    private ICodeGenerationService codeGenerationService;

    public CodeGenerationController(ICodeGenerationService codeGenerationService) {
        this.codeGenerationService = codeGenerationService;
    }

    public void generateCode() {
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("starting generateCode()");
            }
            getContext().getLog().reset();
            CodeGenerationTask t = newTaskInstance();
            t.execute();
            if (LOG.isDebugEnabled()) {
                LOG.debug("end generateCode()");
            }
        } catch (IOException e) {
            throw new AnycodeRuntimeException(e);
        }
    }

    public void updateTemplatesAndMacrosCount(final String templatePath) {
        List<ICodeGenerationArtifact> artifacts = getCodeGenerationService().getCodeGenerationArtifacts(templatePath);
        Integer macros = 0;
        Integer templates = 0;
        for (ICodeGenerationArtifact art : artifacts) {
            if (art instanceof IMacro) {
                macros++;
            } else if (art instanceof ITemplate) {
                templates++;
            }
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("updateTemplatesAndMacrosCount(" + templatePath + ")");
        }
        context.setTemplatesAndMacrosCount(macros, templates);
    }

    private ICodeGenerationService getCodeGenerationService() {
        return this.codeGenerationService;
    }

    public void reset() {
        getContext().getLog().reset();
    }

    private CodeGenerationTask newTaskInstance() throws IOException {
        getCodeGenerationService().attachReport(getContext().getLog());
        CodeGenerationTask t = new CodeGenerationTask(getCodeGenerationService(), getContext().getTemplatePath(),
                getContext().getOutputPath());
        return t;
    }

    @Override
    public ICodeGenerationContext getContext() {
        return this.context;
    }

    @Override
    public void register(ICodeGenerationContext context) {
        this.context = context;
        this.context.setDefaultBrowsePath(getCodeGenerationService().getModelFilePath());
    }

}

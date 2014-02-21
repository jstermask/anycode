package in.labulle.anycode.astah.plugin.generator.service.impl;

import in.labulle.anycode.astah.plugin.template.config.Configuration;
import in.labulle.anycode.engine.core.ICodeGenerationArtifact;
import in.labulle.anycode.engine.core.ITemplate;
import in.labulle.anycode.engine.core.ITemplateRepositoryFactory;
import in.labulle.anycode.engine.exception.TemplateException;
import in.labulle.anycode.engine.log.ICodeGenerationLog;
import in.labulle.anycode.engine.service.ICodeGenerationService;
import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.repository.IModelRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Gets all the classes from the model and generates them with each template.
 * 
 * @author José Carreno
 * 
 */
public class ClassCodeGenerationServiceImpl implements ICodeGenerationService {
    /**
     * log.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ClassCodeGenerationServiceImpl.class);

    /**
     * Model repository.
     */
    private IModelRepository modelRepository;

    /**
     * Template repository.
     */
    private ITemplateRepositoryFactory templateRepositoryFactory;

    /**
     * Code generation report.
     */
    private ICodeGenerationLog codeGenerationReport;

    /**
     * 
     * @param modelFactory
     * @param templateRepository
     */
    public ClassCodeGenerationServiceImpl(final IModelRepository modelFactory,
            final ITemplateRepositoryFactory templateRepository) {
        this.modelRepository = modelFactory;
        this.templateRepositoryFactory = templateRepository;
    }

    @Override
    public void generateCode(final String templatePath, final String outputPath) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("generateCode(" + templatePath + ", " + outputPath + ")");
        }
        Configuration config = Configuration.getConfiguration().clone();
        if (LOG.isDebugEnabled()) {
            LOG.debug("cloned configuration");
        }
        config.put(Configuration.CONTEXT_PARAM_TARGET_DIR, outputPath);
        config.put(Configuration.CONTEXT_PARAM_TEMPLATE_DIR, templatePath);
        List<IClass> classes = getModelRepository().getModelInstance().getAllClasses();
        List<ITemplate> templates = getTemplates(templatePath);
        int maxGenerations = classes.size() * templates.size();
        if (LOG.isDebugEnabled()) {
            LOG.debug(classes.size() + " classes and " + templates.size() + " templates found !");
        }
        int achievedGenerations = 0;
        for (IClass aClass : classes) {
            for (ITemplate aTemplate : templates) {
                achievedGenerations++;
                if (codeGenerationReport != null) {
                    codeGenerationReport.setProgress((int) Math.round(((achievedGenerations * 1.0)
                            / (maxGenerations * 1.0) * 100.0)));
                }
                generate(aClass, aTemplate, config);
            }
        }
    }

    /**
     * 
     * @return template repository
     */
    protected ITemplateRepositoryFactory getTemplateRepositoryFactory() {
        return templateRepositoryFactory;
    }

    /**
     * 
     * @return model repository
     */
    protected IModelRepository getModelRepository() {
        return modelRepository;
    }

    /**
     * Call the template to render itself as a file.
     * 
     * @param aClass
     *            class
     * @param aTemplate
     *            template
     * @param config
     *            configuration
     */
    private void generate(final IClass aClass, final ITemplate aTemplate, final Configuration config) {
        config.put(Configuration.CONTEXT_PARAM_CLASS_CURRENT, aClass);
        if (LOG.isDebugEnabled()) {
            LOG.debug("generate(" + aClass.getName() + ", " + aTemplate + "," + config + ")");
        }
        try {
            File f = aTemplate.renderToFile(config);
            if (this.codeGenerationReport != null) {
                this.codeGenerationReport.success(f, aTemplate.getName(), aClass.getFullyQualifiedName("."));
            }
        } catch (TemplateException e) {
            if (this.codeGenerationReport != null) {
                this.codeGenerationReport.failure(aTemplate.getName(), aClass.getFullyQualifiedName("."), e);
            }
        }
    }

    @Override
    public void attachReport(ICodeGenerationLog report) {
        this.codeGenerationReport = report;
    }

    private List<ITemplate> getTemplates(String templatePath) {
        List<ITemplate> templates = new ArrayList<ITemplate>();
        List<ICodeGenerationArtifact> arts = this.templateRepositoryFactory.newInstance(templatePath).getCodeGenerationArtifacts();
        for (ICodeGenerationArtifact art : arts) {
            if (art instanceof ITemplate) {
                templates.add((ITemplate) art);
            }
        }
        return templates;
    }

    @Override
    public List<ICodeGenerationArtifact> getCodeGenerationArtifacts(String templatePath) {
        return this.templateRepositoryFactory.newInstance(templatePath).getCodeGenerationArtifacts();

    }

    @Override
    public String getModelFilePath() {
        return getModelRepository().getModelFilePath();
    }

}

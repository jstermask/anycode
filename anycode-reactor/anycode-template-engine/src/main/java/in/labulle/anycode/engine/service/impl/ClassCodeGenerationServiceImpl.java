package in.labulle.anycode.engine.service.impl;

import in.labulle.anycode.engine.config.Configuration;
import in.labulle.anycode.engine.core.ICodeGenerationArtifact;
import in.labulle.anycode.engine.core.IMacro;
import in.labulle.anycode.engine.core.ITemplate;
import in.labulle.anycode.engine.exception.TemplateRuntimeException;
import in.labulle.anycode.engine.log.ICodeGenerationLog;
import in.labulle.anycode.engine.repository.ITemplateRepositoryFactory;
import in.labulle.anycode.engine.service.ICodeGenerationService;
import in.labulle.anycode.engine.service.handler.ClassifierCodeGenerator;
import in.labulle.anycode.engine.service.handler.ICodeGenerator;
import in.labulle.anycode.engine.service.handler.ModelCodeGenerator;
import in.labulle.anycode.engine.service.handler.ReportCodeGenerator;
import in.labulle.anycode.engine.service.util.TemplateUtils;
import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.uml.repository.IModelRepository;

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
	private static final Logger LOG = LoggerFactory
			.getLogger(ClassCodeGenerationServiceImpl.class);

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
	 * Code generators
	 */
	@SuppressWarnings("unchecked")
	private Class<? extends ReportCodeGenerator>[] codeGeneratorClasses = new Class[] {
			ClassifierCodeGenerator.class, ModelCodeGenerator.class };

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

	public void generateCode(final String templatePath, final String outputPath) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("generateCode(" + templatePath + ", " + outputPath + ")");
		}
		Configuration config = Configuration.getConfiguration().clone();
		config.put(Configuration.CONTEXT_PARAM_TARGET_DIR, outputPath);
		config.put(Configuration.CONTEXT_PARAM_TEMPLATE_DIR, templatePath);
		IModel model = getModelRepository().getModelInstance();
		List<ICodeGenerationArtifact> arts = getCodeGenerationArtifacts(templatePath);
		List<ITemplate> templates = TemplateUtils.getTemplates(arts);
		List<IMacro> macros = TemplateUtils.getMacros(arts);
		addMacros(macros, config);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Number of templates : " + templates.size());
		}

		List<ICodeGenerator> generators = newCodeGenerators(config);
		if (codeGenerationReport != null) {
			int maxGenerations = 0;
			for (ICodeGenerator gen : generators) {
				maxGenerations += gen.calculateNumberOfGenerations(model,
						templates);
			}
			codeGenerationReport.setNumberOfGenerations(maxGenerations);
		}

		for(ICodeGenerator gen : generators) {
			gen.generateCode(model, templates);
		}
		
	}


	/**
	 * 
	 * @return model repository
	 */
	protected IModelRepository getModelRepository() {
		return modelRepository;
	}

	private void addMacros(List<IMacro> macros, final Configuration config) {
		for (IMacro aMacro : macros) {
			config.put(aMacro.getVarName(), aMacro.getInstance());
		}
	}

	public void attachReport(ICodeGenerationLog report) {
		this.codeGenerationReport = report;
	}

	public List<ICodeGenerationArtifact> getCodeGenerationArtifacts(
			String templatePath) {
		return this.templateRepositoryFactory.newInstance(templatePath)
				.getCodeGenerationArtifacts();

	}

	public String getModelFilePath() {
		return getModelRepository().getModelFilePath();
	}

	private ICodeGenerator initGenerator(
			Class<? extends ReportCodeGenerator> aClass, Configuration config) {
		try {
			ReportCodeGenerator gen = aClass.newInstance();
			gen.setCodeGenerationLog(this.codeGenerationReport);
			gen.setConfiguration(config.clone());
			return gen;
		} catch (Exception e) {
			throw new TemplateRuntimeException(e);
		}
	}

	private List<ICodeGenerator> newCodeGenerators(Configuration config) {
		List<ICodeGenerator> gens = new ArrayList<ICodeGenerator>();
		for (Class c : this.codeGeneratorClasses) {
			gens.add(initGenerator(c, config));
		}
		return gens;
	}

}

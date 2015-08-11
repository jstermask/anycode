package in.labulle.anycode.engine.service.handler;

import in.labulle.anycode.engine.config.Configuration;
import in.labulle.anycode.engine.core.ITemplate;
import in.labulle.anycode.engine.core.TemplateScope;
import in.labulle.anycode.engine.exception.TemplateException;
import in.labulle.anycode.engine.service.util.TemplateUtils;
import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IModel;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassifierCodeGenerator extends ReportCodeGenerator {
	/**
	 * log.
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(ClassifierCodeGenerator.class);

	public void generateCode(IModel model, List<ITemplate> templates) {
		List<IClassifier> classes = model.getAllClasses();
		List<ITemplate> tps = TemplateUtils.getTemplateByScope(templates, TemplateScope.CLASSIFIER);
		for (IClassifier aClass : classes) {
			for (ITemplate aTemplate : tps) {
				getCodeGenerationLog().progress();
				generate(aClass, aTemplate);
			}
		}
	}

	private void generate(final IClassifier aClass, final ITemplate aTemplate) {
		getConfiguration().put(Configuration.CONTEXT_PARAM_CLASS_CURRENT,
				aClass);
		if (LOG.isDebugEnabled()) {
			LOG.debug("generate(" + aClass.getName() + ","
					+ aTemplate.getName() + ")");
		}
		try {
			File f = aTemplate.renderToFile(getConfiguration());
			getCodeGenerationLog().success(f, aTemplate.getName(),
					aClass.getFullyQualifiedName("."));
		} catch (TemplateException e) {

			getCodeGenerationLog().failure(aTemplate.getName(),
					aClass.getFullyQualifiedName("."), e);
		}
	}

	public Integer calculateNumberOfGenerations(IModel model,
			List<ITemplate> templates) {
		return model.getAllClasses().size() * TemplateUtils.getTemplateByScope(templates, TemplateScope.CLASSIFIER).size();
	}

}

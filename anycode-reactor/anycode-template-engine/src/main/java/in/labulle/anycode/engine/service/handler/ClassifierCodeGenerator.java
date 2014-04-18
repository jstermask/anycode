package in.labulle.anycode.engine.service.handler;

import in.labulle.anycode.engine.config.Configuration;
import in.labulle.anycode.engine.core.ITemplate;
import in.labulle.anycode.engine.core.TemplateScope;
import in.labulle.anycode.engine.exception.TemplateException;
import in.labulle.anycode.uml.IClass;
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
		List<IClass> classes = model.getAllClasses();
		for (IClass aClass : classes) {
			for (ITemplate aTemplate : templates) {
				if (TemplateScope.CLASSIFIER.equals(aTemplate.getScope())) {
					generate(aClass, aTemplate);
				}
			}
		}

	}

	private void generate(final IClass aClass, final ITemplate aTemplate) {
		getConfiguration().put(Configuration.CONTEXT_PARAM_CLASS_CURRENT, aClass);
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

}

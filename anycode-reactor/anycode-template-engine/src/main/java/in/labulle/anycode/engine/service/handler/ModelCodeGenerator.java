package in.labulle.anycode.engine.service.handler;

import in.labulle.anycode.engine.config.Configuration;
import in.labulle.anycode.engine.core.ITemplate;
import in.labulle.anycode.engine.core.TemplateScope;
import in.labulle.anycode.engine.exception.TemplateException;
import in.labulle.anycode.engine.service.util.TemplateUtils;
import in.labulle.anycode.uml.IModel;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelCodeGenerator extends ReportCodeGenerator {
	/**
	 * log.
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(ModelCodeGenerator.class);

	public void generateCode(IModel model, List<ITemplate> templates) {
		List<ITemplate> tps = TemplateUtils.getTemplateByScope(templates,
				TemplateScope.MODEL);
		for (ITemplate aTemplate : tps) {
			generate(model, aTemplate);
			if (getCodeGenerationLog() != null) {
				getCodeGenerationLog().progress();
			}
		}
	}

	private void generate(final IModel model, final ITemplate aTemplate) {
		getConfiguration()
				.put(Configuration.CONTEXT_PARAM_MODEL_CURRENT, model);
		if (LOG.isDebugEnabled()) {
			LOG.debug("generate(" + model + "," + aTemplate.getName() + ")");
		}
		try {
			File f = aTemplate.renderToFile(getConfiguration());
			if (getCodeGenerationLog() != null) {
				getCodeGenerationLog().success(f, aTemplate.getName(),
						model.toString());
			}
		} catch (TemplateException e) {
			if (getCodeGenerationLog() != null) {
				getCodeGenerationLog().failure(aTemplate.getName(),
						model.toString(), e);
			}
		}
	}

	public Integer calculateNumberOfGenerations(IModel model,
			List<ITemplate> templates) {
		return TemplateUtils.getTemplateByScope(templates, TemplateScope.MODEL)
				.size();
	}

}

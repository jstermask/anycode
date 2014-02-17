package in.labulle.anycode.astah.plugin.template.freemarker.core;

import in.labulle.anycode.astah.plugin.template.api.ITemplate;
import in.labulle.anycode.astah.plugin.template.config.Configuration;
import in.labulle.anycode.astah.plugin.template.exception.TemplateException;

import java.io.File;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.change_vision.jude.api.inf.model.IClass;


public class CompositeTemplate implements ITemplate {
	private static final Logger LOG = LoggerFactory
			.getLogger(CompositeTemplate.class);

	private final ITemplate fileNameTemplate;
	private final ITemplate template;

	public CompositeTemplate(ITemplate template, ITemplate fileNameTemplate) {
		this.template = template;
		this.fileNameTemplate = fileNameTemplate;
	}

	protected IClass getClassToRender(Map<String, Object> context) {
		return ((IClass) context.get(Configuration.CONTEXT_PARAM_CLASS_CURRENT));
	}

	@Override
	public boolean overrides() {
		return template.overrides() && fileNameTemplate.overrides();
	}

	@Override
	public String renderAsString(Map<String, Object> context) throws TemplateException {
		return template.renderAsString(context);
	}

	@Override
	public File renderToFile(Map<String, Object> context) throws TemplateException {
		String outputFileName = fileNameTemplate.renderAsString(context);
		if (outputFileName != null && !"".equals(outputFileName.trim())) {
			context.put(Template.OUTPUT_FILE_CONTEXT_PARAM, outputFileName);
			return template.renderToFile(context);
		} else {
			if (LOG.isWarnEnabled()) {
				LOG.warn(this + " won't render because " + fileNameTemplate
						+ " rendered an empty string !");
			}
			return null;
		}
	}


	@Override
	public String toString() {
		return "Template {file:" + fileNameTemplate + ",content:" + template
				+ "}";
	}

	@Override
	public String getName() {
	    int suffixIdx = fileNameTemplate.getName().lastIndexOf("-");
		return fileNameTemplate.getName().substring(0, suffixIdx);
	}




}

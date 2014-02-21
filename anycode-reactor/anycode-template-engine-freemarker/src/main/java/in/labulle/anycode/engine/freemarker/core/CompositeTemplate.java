package in.labulle.anycode.engine.freemarker.core;

import in.labulle.anycode.engine.config.Configuration;
import in.labulle.anycode.engine.core.ITemplate;
import in.labulle.anycode.engine.exception.TemplateException;
import in.labulle.anycode.uml.IClass;

import java.io.File;
import java.util.Map;


public class CompositeTemplate implements ITemplate {
	
	private final ITemplate fileNameTemplate;
	private final ITemplate template;

	public CompositeTemplate(ITemplate template, ITemplate fileNameTemplate) {
		this.template = template;
		this.fileNameTemplate = fileNameTemplate;
	}

	protected IClass getClassToRender(Map<String, Object> context) {
		return ((IClass) context.get(Configuration.CONTEXT_PARAM_CLASS_CURRENT));
	}


	public boolean overrides() {
		return template.overrides() && fileNameTemplate.overrides();
	}


	public String renderAsString(Map<String, Object> context) throws TemplateException {
		return template.renderAsString(context);
	}


	public File renderToFile(Map<String, Object> context) throws TemplateException {
		String outputFileName = fileNameTemplate.renderAsString(context);
		if (outputFileName != null && !"".equals(outputFileName.trim())) {
			context.put(Template.OUTPUT_FILE_CONTEXT_PARAM, outputFileName);
			return template.renderToFile(context);
		} else {
			return null;
		}
	}



	public String toString() {
		return "Template {file:" + fileNameTemplate + ",content:" + template
				+ "}";
	}


	public String getName() {
	    int suffixIdx = fileNameTemplate.getName().lastIndexOf("-");
		return fileNameTemplate.getName().substring(0, suffixIdx);
	}




}

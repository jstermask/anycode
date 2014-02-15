package in.labulle.anycode.engine;

import groovy.text.GStringTemplateEngine;
import groovy.text.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Map;

public class GroovyTemplate implements ITemplate {

	private GStringTemplateEngine templateEngine;

	private File nameTemplate;

	private File contentTemplate;

	public GroovyTemplate(final String nameTemplatePath,
			final String contentTemplatePath) {
		this.templateEngine = new GStringTemplateEngine();
		this.nameTemplate = new File(nameTemplatePath);
		this.contentTemplate = new File(contentTemplatePath);
	}

	public void render(Map<String, Object> context) throws TemplateException {
		StringWriter sw = new StringWriter();
		try {
			Template t = templateEngine.createTemplate(nameTemplate);
			t.make(context).writeTo(sw);

			String targetDir = (String) context.get(TARGET_DIR_PARAM);
			if (targetDir == null) {
				throw new TemplateException(
						"Target directory is not specified. Please set a varable named "
								+ TARGET_DIR_PARAM
								+ " that points to a valid writable directory");
			} else {
				File f = new File(targetDir);
				f.mkdirs();
				String outputPath = sw.toString();
				FileWriter fw = new FileWriter(new File(outputPath));
				Template ct = templateEngine.createTemplate(contentTemplate);
				ct.make(context).writeTo(fw);
				fw.close();
			}
		} catch (Exception e) {
			throw new TemplateException(e);
		}

	}
}

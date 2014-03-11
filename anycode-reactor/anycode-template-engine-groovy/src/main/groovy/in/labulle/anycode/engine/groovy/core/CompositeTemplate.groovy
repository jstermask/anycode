package in.labulle.anycode.engine.groovy.core

import groovy.text.GStringTemplateEngine
import groovy.text.Template
import in.labulle.anycode.engine.core.ITemplate
import in.labulle.anycode.engine.exception.TemplateException

class CompositeTemplate implements ITemplate {
	GStringTemplateEngine engine
	Template nameTemplate
	Template contentTemplate
	String name

	def CompositeTemplate() {
		engine = new GStringTemplateEngine()
	}

	def void setNameTemplate(String namePath) {
		nameTemplate = engine.createTemplate(new File(namePath))
		calculateName(namePath)
	}

	def void setContentTemplate(String contentPath) {
		contentTemplate = engine.createTemplate(new File(contentPath))
		calculateName(contentPath)
	}

	def void calculateName(String path) {
		name = path.substring(path.lastIndexOf(File.separator), path.lastIndexOf("."))
	}

	def void render(Map<String, Object> ctx) {
		loadDirectives(ctx);
		def outputFile = nameTemplate.make(ctx).toString()
		File f = new File(outputFile)
		f.getParentFile().mkdirs();
		FileWriter w = new FileWriter(f)
		contentTemplate.make(ctx).writeTo(w)
		w.close();
	}

	def void loadDirectives(Map<String, Object> ctx) {
		ctx.put("java", new JavaDirective())
		ctx.put("util", new UtilDirective())
		ctx.put("jpa", new JpaDirective())
	}

	public String getName() {
		return name
	}

	public boolean overrides() {
		return true;
	}

	public String renderAsString(Map<String, Object> ctx) throws TemplateException {
		try {
			loadDirectives(ctx);
			return contentTemplate.make(ctx).toString()
		} catch(Exception e) {
			throw new TemplateException(e);
		}
	}

	public File renderToFile(Map<String, Object> ctx) throws TemplateException {
		try {
			loadDirectives(ctx);
			def outputFile = nameTemplate.make(ctx).toString()
			def content = contentTemplate.make(ctx).toString()
			def contentStr = content.replaceAll("\n", "").replaceAll("\t", "").replaceAll(" ", "")
			if(contentStr.length() > 1) {
				File f = new File(outputFile)
				f.getParentFile().mkdirs();
				FileWriter w = new FileWriter(f)
				def gs = """${content}"""
				gs.writeTo(w)
				w.close();
				return f;
			} else {
				return null;
			}
		} catch(Exception e) {
			throw new TemplateException(e);
		}
	}
}

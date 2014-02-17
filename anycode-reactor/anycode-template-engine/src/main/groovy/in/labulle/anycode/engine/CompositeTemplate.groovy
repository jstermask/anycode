package in.labulle.anycode.engine

import groovy.text.GStringTemplateEngine
import groovy.text.Template

class CompositeTemplate implements ITemplate {
	GStringTemplateEngine engine
	Template nameTemplate
	Template contentTemplate
	
	def CompositeTemplate() {
		engine = new GStringTemplateEngine()	
	}
	
	def void setNameTemplate(String namePath) {
		nameTemplate = engine.createTemplate(new File(namePath))
	}
	
	def void setContentTemplate(String contentPath) {
		contentTemplate = engine.createTemplate(new File(contentPath))
	}
	
	def void render(Map<String, Object> ctx) {
		ctx.put("jpa", new JpaDirective())
		ctx.put("java", new JavaDirective())
		def outputFile = nameTemplate.make(ctx).toString()
		File f = new File(outputFile)
		f.getParentFile().mkdirs();
		FileWriter w = new FileWriter(f)
		contentTemplate.make(ctx).writeTo(w)
		w.close();
	}
	
}

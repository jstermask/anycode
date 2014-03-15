package in.labulle.anycode.engine.groovy.repository

import in.labulle.anycode.engine.core.IMacro
import in.labulle.anycode.engine.core.ITemplate
import in.labulle.anycode.engine.groovy.core.CompositeTemplate
import in.labulle.anycode.engine.groovy.core.GroovyUserClass
import in.labulle.anycode.engine.repository.AbstractDirectoryTemplateRepository

class TemplateRepositoryImpl extends AbstractDirectoryTemplateRepository {

	
	
	public TemplateRepositoryImpl(String path) {
		super(path);
	}

	@Override
	protected ITemplate buildTemplate(String nameTemplatePath,
			String contentTemplatePath) {
		CompositeTemplate t = new CompositeTemplate()
		t.setContentTemplate(contentTemplatePath)
		t.setNameTemplate(nameTemplatePath)
		return t
	}
	
	@Override
	protected IMacro buildMacro(String macroPath) {
		return new GroovyUserClass(new File(macroPath))
	}
}

package in.labulle.anycode.engine.groovy.repository

import in.labulle.anycode.engine.repository.ITemplateRepository
import in.labulle.anycode.engine.repository.ITemplateRepositoryFactory;

class TemplateRepositoryFactoryImpl implements ITemplateRepositoryFactory {

	public ITemplateRepository newInstance(String templatePath) {
		return new TemplateRepositoryImpl(templatePath)
	}

}

package in.labulle.anycode.engine.repository;

import in.labulle.anycode.engine.exception.TemplateRuntimeException;

public class TemplateRepositoryFactoryImpl implements
		ITemplateRepositoryFactory {

	private Engine engine;

	public TemplateRepositoryFactoryImpl(Engine engineName) {
		this.engine = engineName;
	}

	@SuppressWarnings("unchecked")
	public ITemplateRepository newInstance(String templatePath) {
		Class<? extends ITemplateRepositoryFactory> c;
		try {
			c = (Class<ITemplateRepositoryFactory>) Class
					.forName("in.labulle.anycode.engine." + engine
							+ ".repository.TemplateRepositoryFactoryImpl");
			ITemplateRepositoryFactory factory = c.newInstance();
			return factory.newInstance(templatePath);
		} catch (Exception e) {
			throw new TemplateRuntimeException(e);
		}

	}

}

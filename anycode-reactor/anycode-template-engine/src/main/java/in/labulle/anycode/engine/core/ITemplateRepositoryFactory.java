package in.labulle.anycode.engine.core;


public interface ITemplateRepositoryFactory {
	ITemplateRepository newInstance(final String templatePath);
}

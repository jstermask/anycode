package in.labulle.anycode.engine.repository;


public interface ITemplateRepositoryFactory {
	ITemplateRepository newInstance(final String templatePath);
}

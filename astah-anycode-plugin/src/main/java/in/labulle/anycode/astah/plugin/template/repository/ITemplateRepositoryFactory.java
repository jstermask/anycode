package in.labulle.anycode.astah.plugin.template.repository;


public interface ITemplateRepositoryFactory {
	ITemplateRepository newInstance(final String templatePath);
}

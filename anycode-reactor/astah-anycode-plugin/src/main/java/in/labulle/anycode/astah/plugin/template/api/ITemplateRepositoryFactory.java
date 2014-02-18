package in.labulle.anycode.astah.plugin.template.api;


public interface ITemplateRepositoryFactory {
	ITemplateRepository newInstance(final String templatePath);
}

package in.labulle.anycode.astah.plugin.template.repository.impl;

import java.io.File;
import java.io.IOException;

import in.labulle.anycode.astah.plugin.exception.AnycodeRuntimeException;
import in.labulle.anycode.astah.plugin.osgi.Activator;
import in.labulle.anycode.astah.plugin.template.freemarker.loader.BundleTemplateLoader;
import in.labulle.anycode.astah.plugin.template.freemarker.loader.FilePathTemplateLoader;
import in.labulle.anycode.astah.plugin.template.freemarker.loader.ITemplateLoader;
import in.labulle.anycode.astah.plugin.template.freemarker.loader.MixedTemplateLoader;
import in.labulle.anycode.astah.plugin.template.repository.ITemplateRepository;
import in.labulle.anycode.astah.plugin.template.repository.ITemplateRepositoryFactory;

public class TemplateRepositoryFactoryImpl implements
		ITemplateRepositoryFactory {

	@Override
	public ITemplateRepository newInstance(String templatePath) {
		try {
		return new TemplateRepositoryImpl(
				new MixedTemplateLoader(new ITemplateLoader[] {
						new FilePathTemplateLoader(new File(
								templatePath)),
						new BundleTemplateLoader(
								Activator.getBundleContext()) }));
		} catch(IOException e) {
			throw new AnycodeRuntimeException(e);
		}
	}

}

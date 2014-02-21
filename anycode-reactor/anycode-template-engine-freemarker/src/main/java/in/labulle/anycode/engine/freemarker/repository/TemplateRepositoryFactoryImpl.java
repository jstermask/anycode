package in.labulle.anycode.engine.freemarker.repository;

import in.labulle.anycode.engine.core.ITemplateRepository;
import in.labulle.anycode.engine.core.ITemplateRepositoryFactory;
import in.labulle.anycode.engine.exception.TemplateRuntimeException;
import in.labulle.anycode.engine.freemarker.repository.loader.BundleTemplateLoader;
import in.labulle.anycode.engine.freemarker.repository.loader.FilePathTemplateLoader;
import in.labulle.anycode.engine.freemarker.repository.loader.ITemplateLoader;
import in.labulle.anycode.engine.freemarker.repository.loader.MixedTemplateLoader;
import in.labulle.anycode.engine.freemarker.repository.loader.osgi.Activator;

import java.io.File;
import java.io.IOException;

public class TemplateRepositoryFactoryImpl implements ITemplateRepositoryFactory {


    public ITemplateRepository newInstance(String templatePath) {
        try {
            return new TemplateRepositoryImpl(new MixedTemplateLoader(new ITemplateLoader[] {
                    new FilePathTemplateLoader(new File(templatePath)),
                    new BundleTemplateLoader(Activator.getBundleContext()) }));
        } catch (IOException e) {
            throw new TemplateRuntimeException(e);
        }
    }

}

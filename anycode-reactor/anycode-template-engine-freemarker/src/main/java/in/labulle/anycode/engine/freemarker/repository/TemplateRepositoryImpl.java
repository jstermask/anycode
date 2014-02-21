package in.labulle.anycode.astah.plugin.template.freemarker.repository;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import in.labulle.anycode.astah.plugin.template.freemarker.loader.ITemplateLoader;
import in.labulle.anycode.engine.core.ICodeGenerationArtifact;
import in.labulle.anycode.engine.core.ITemplateRepository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Discovers automatically templates
 * 
 * @author A44447
 * 
 */
public class TemplateRepositoryImpl implements ITemplateRepository {
	private static final Logger LOG = LoggerFactory
			.getLogger(TemplateRepositoryImpl.class);

	private boolean initialized = false;

	private final ITemplateLoader templateLoader;

	private final List<ICodeGenerationArtifact> codeGenerationArtifacts = new ArrayList<ICodeGenerationArtifact>();

	public TemplateRepositoryImpl(ITemplateLoader tpl) {
		this.templateLoader = tpl;
	}

	private void fetchTemplatesAndMacros() {
		codeGenerationArtifacts.clear();
		Configuration tConfig = new Configuration();
		tConfig.setLocalizedLookup(false);
		tConfig.setObjectWrapper(new DefaultObjectWrapper());
		ITemplateLoader tpLoader = getTemplateLoader();
		tConfig.setTemplateLoader(tpLoader);
		List<ICodeGenerationArtifact> tps = tpLoader.load(tConfig);
		codeGenerationArtifacts.addAll(tps);
	}

	private ITemplateLoader getTemplateLoader() {
		return templateLoader;
	}

	private boolean isInitialized() {
		return initialized;
	}

	private void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	@Override
	public void refresh() {
		setInitialized(false);
	}

	@Override
	public List<ICodeGenerationArtifact> getCodeGenerationArtifacts() {
		if (!isInitialized()) {
			fetchTemplatesAndMacros();
			setInitialized(true);
		}
		return this.codeGenerationArtifacts;
	}

}

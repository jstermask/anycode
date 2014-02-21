package in.labulle.anycode.engine.freemarker.repository;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import in.labulle.anycode.engine.core.ICodeGenerationArtifact;
import in.labulle.anycode.engine.freemarker.repository.loader.ITemplateLoader;
import in.labulle.anycode.engine.repository.ITemplateRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Discovers automatically templates
 * 
 * @author A44447
 * 
 */
public class TemplateRepositoryImpl implements ITemplateRepository {
	
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

	
	public void refresh() {
		setInitialized(false);
	}

	
	public List<ICodeGenerationArtifact> getCodeGenerationArtifacts() {
		if (!isInitialized()) {
			fetchTemplatesAndMacros();
			setInitialized(true);
		}
		return this.codeGenerationArtifacts;
	}

}

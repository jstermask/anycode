package in.labulle.anycode.engine.freemarker.repository;

import java.util.List;

import in.labulle.anycode.engine.core.ICodeGenerationArtifact;
import in.labulle.anycode.engine.repository.ITemplateRepository;
import in.labulle.anycode.engine.repository.ITemplateRepositoryFactory;

public class TemplateRepositoryFactoryImpl implements ITemplateRepositoryFactory {

	public ITemplateRepository newInstance(String templatePath) {
		return new ITemplateRepository() {
			
			public void refresh() {

				
			}
			
			public List<ICodeGenerationArtifact> getCodeGenerationArtifacts() {
				return null;
			}
		};
	}

}

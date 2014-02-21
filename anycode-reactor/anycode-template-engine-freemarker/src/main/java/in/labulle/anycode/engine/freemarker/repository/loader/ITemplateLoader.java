package in.labulle.anycode.engine.freemarker.repository.loader;

import in.labulle.anycode.engine.core.ICodeGenerationArtifact;

import java.util.List;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;

public interface ITemplateLoader extends TemplateLoader {
	List<ICodeGenerationArtifact> load(Configuration tConfiguration);
}

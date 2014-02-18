package in.labulle.anycode.astah.plugin.template.freemarker.loader;

import in.labulle.anycode.astah.plugin.template.api.ICodeGenerationArtifact;

import java.util.List;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;

public interface ITemplateLoader extends TemplateLoader {
	List<ICodeGenerationArtifact> load(Configuration tConfiguration);
}

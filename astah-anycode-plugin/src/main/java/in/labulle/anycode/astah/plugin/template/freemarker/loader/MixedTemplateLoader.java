package in.labulle.anycode.astah.plugin.template.freemarker.loader;

import in.labulle.anycode.astah.plugin.template.api.ICodeGenerationArtifact;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;

public class MixedTemplateLoader implements ITemplateLoader {
	private final ITemplateLoader[] loaders;

	public MixedTemplateLoader(ITemplateLoader[] loaders) {
		this.loaders = loaders;
	}

	@Override
	public void closeTemplateSource(Object templateSource) throws IOException {
		boolean done = false;
		for (TemplateLoader loader : loaders) {
			try {
				loader.closeTemplateSource(templateSource);
				done = true;
			} catch (Exception e) {

			}
		}
		if (!done) {
			throw new IOException("Could not close template " + templateSource);
		}

	}

	@Override
	public Object findTemplateSource(String name) throws IOException {
		for (TemplateLoader loader : loaders) {
			try {
				Object o = loader.findTemplateSource(name);
				if (o != null) {
					return o;
				}
			} catch (Exception e) {

			}
		}
		throw new IOException("Could not find template " + name);

	}

	@Override
	public long getLastModified(Object templateSource) {
		long lastMod = 0;
		for (TemplateLoader loader : loaders) {
			try {
				long newLastMod = loader.getLastModified(templateSource);
				if(newLastMod > lastMod) {
					lastMod = newLastMod;
				}
			} catch (Exception e) {

			}
		}
		return lastMod;
	}

	@Override
	public Reader getReader(Object templateSource, String encoding)
			throws IOException {
		for (TemplateLoader loader : loaders) {
			try {
				Reader r = loader.getReader(templateSource, encoding);
				if (r != null) {
					return r;
				}
			} catch (Exception e) {

			}
		}
		throw new IOException("Could not getReader " + templateSource + " - " + encoding);
	}

	@Override
	public List<ICodeGenerationArtifact> load(Configuration tConfiguration) {
		List<ICodeGenerationArtifact> tp = new ArrayList<ICodeGenerationArtifact>();
		for(ITemplateLoader loader : loaders) {
			tp.addAll(loader.load(tConfiguration));
		}
		return tp;
	}

}

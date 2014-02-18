package in.labulle.anycode.astah.plugin.template.freemarker.loader;

import freemarker.template.Configuration;
import in.labulle.anycode.astah.plugin.osgi.util.BundleUtils;
import in.labulle.anycode.astah.plugin.template.api.ICodeGenerationArtifact;
import in.labulle.anycode.astah.plugin.template.exception.InvalidMacroNameException;
import in.labulle.anycode.astah.plugin.template.freemarker.core.Macro;
import in.labulle.anycode.astah.plugin.template.util.TemplateUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BundleTemplateLoader implements ITemplateLoader {
	private static final Logger LOG = LoggerFactory
			.getLogger(BundleTemplateLoader.class);

	private final BundleContext context;

	public BundleTemplateLoader(BundleContext context) {
		this.context = context;
	}

	@Override
	public void closeTemplateSource(Object templateSource) throws IOException {
		if (templateSource != null) {
			InputStream is = (InputStream) templateSource;
			is.close();
		}

	}

	@Override
	public Object findTemplateSource(String name) throws IOException {
		URL url = BundleUtils.getResourcePath(context, name);
		if (url != null) {
			return url.openStream();
		} else {
			return null;
		}
	}

	@Override
	public long getLastModified(Object templateSource) {
		return 1375571931028L;
	}

	@Override
	public Reader getReader(Object templateSource, String encoding)
			throws IOException {
		InputStreamReader rd = new InputStreamReader(
				(InputStream) templateSource, encoding);
		return rd;
	}

	@Override
	public List<ICodeGenerationArtifact> load(Configuration tConfiguration) {
		List<ICodeGenerationArtifact> tps = new ArrayList<ICodeGenerationArtifact>();
		addMacros(tConfiguration, tps);
		return tps;
	}

	private void addMacros(freemarker.template.Configuration tConfig, List<ICodeGenerationArtifact> arts) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Looking for internal macros");
		}
		List<URL> macros = BundleUtils.findResources(context, "*.mdm");
		if (macros != null) {
			for (URL u : macros) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("Found macro : " + u.getFile());
				}
				try {
					String[] macroAttributes = TemplateUtils
							.tokenizeMacroName(u.getFile());
					tConfig.addAutoImport(macroAttributes[0],
							macroAttributes[3]);
					Macro m = new Macro(macroAttributes[3],macroAttributes[0], macroAttributes[1]);
					arts.add(m);
					if (LOG.isDebugEnabled()) {
						LOG.debug("Macro " + macroAttributes[3]
								+ " added to namespace " + macroAttributes[0]);
					}
				} catch (InvalidMacroNameException e) {
					if (LOG.isDebugEnabled()) {
						LOG.debug("Macro " + u.getFile() + " ignored ", e);
					}
				}
			}
		}
	}

}

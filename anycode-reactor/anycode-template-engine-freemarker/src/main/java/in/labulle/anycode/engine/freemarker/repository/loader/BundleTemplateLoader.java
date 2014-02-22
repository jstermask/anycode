package in.labulle.anycode.engine.freemarker.repository.loader;

import freemarker.template.Configuration;
import in.labulle.anycode.engine.core.ICodeGenerationArtifact;
import in.labulle.anycode.engine.exception.InvalidMacroNameException;
import in.labulle.anycode.engine.freemarker.core.Macro;
import in.labulle.anycode.engine.osgi.BundleUtils;
import in.labulle.anycode.engine.util.TemplateUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;

public class BundleTemplateLoader implements ITemplateLoader {

    private final BundleContext context;

    public BundleTemplateLoader(BundleContext context) {
        this.context = context;
    }

    public void closeTemplateSource(Object templateSource) throws IOException {
        if (templateSource != null) {
            InputStream is = (InputStream) templateSource;
            is.close();
        }

    }

    public Object findTemplateSource(String name) throws IOException {
        URL url = BundleUtils.getResourcePath(context, name);
        if (url != null) {
            return url.openStream();
        } else {
            return null;
        }
    }

    public long getLastModified(Object templateSource) {
        return 1375571931028L;
    }

    public Reader getReader(Object templateSource, String encoding) throws IOException {
        InputStreamReader rd = new InputStreamReader((InputStream) templateSource, encoding);
        return rd;
    }

    public List<ICodeGenerationArtifact> load(Configuration tConfiguration) {
        List<ICodeGenerationArtifact> tps = new ArrayList<ICodeGenerationArtifact>();
        addMacros(tConfiguration, tps);
        return tps;
    }

    private void addMacros(freemarker.template.Configuration tConfig, List<ICodeGenerationArtifact> arts) {

        List<URL> macros = BundleUtils.findResources(context, "*.mdm");
        if (macros != null) {
            for (URL u : macros) {

                try {
                    String[] macroAttributes = TemplateUtils.tokenizeMacroName(u.getFile());
                    tConfig.addAutoImport(macroAttributes[0], macroAttributes[3]);
                    Macro m = new Macro(macroAttributes[3], macroAttributes[0], macroAttributes[1]);
                    arts.add(m);

                } catch (InvalidMacroNameException e) {

                }
            }
        }
    }

}

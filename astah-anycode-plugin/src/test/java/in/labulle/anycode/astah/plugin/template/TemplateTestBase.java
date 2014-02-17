package in.labulle.anycode.astah.plugin.template;

import in.labulle.anycode.astah.plugin.template.freemarker.core.Template;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public class TemplateTestBase {
	private Configuration configuration;

	protected Configuration getConfiguration() {
		if (this.configuration == null) {
			initConfiguration();
		}
		return this.configuration;
	}

	protected Template getTemplate(String tName) {
		try {
			return new Template(getConfiguration().getTemplate(tName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void initConfiguration() {
		try {
			URI path = getClass().getResource("/templates").toURI();
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(path));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			this.configuration = cfg;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

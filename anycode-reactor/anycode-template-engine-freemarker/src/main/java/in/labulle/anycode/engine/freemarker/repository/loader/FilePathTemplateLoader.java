package in.labulle.anycode.astah.plugin.template.freemarker.loader;

import in.labulle.anycode.astah.plugin.template.freemarker.core.CompositeTemplate;
import in.labulle.anycode.astah.plugin.template.freemarker.core.Template;
import in.labulle.anycode.engine.core.ICodeGenerationArtifact;
import in.labulle.anycode.engine.core.ITemplate;
import in.labulle.anycode.engine.exception.TemplateRuntimeException;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;

public class FilePathTemplateLoader extends FileTemplateLoader implements
		ITemplateLoader {
	private static final Logger LOG = LoggerFactory
			.getLogger(FilePathTemplateLoader.class);

	private final File baseDir;

	public FilePathTemplateLoader(File baseDir) throws IOException {
		super(baseDir);
		this.baseDir = baseDir;
	}

	@Override
	public List<ICodeGenerationArtifact> load(Configuration tConfiguration) {
		List<ICodeGenerationArtifact> templates = new ArrayList<ICodeGenerationArtifact>();
		addTemplates(baseDir, templates, tConfiguration);
		return templates;
	}

	private void addTemplates(File dir,
			List<ICodeGenerationArtifact> templates,
			freemarker.template.Configuration tConfig) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Looking for templates in " + dir.getAbsolutePath()
					+ " directory...");
		}

		String fileNames[] = dir.list(getTemplateFilenameFilter());
		String files[] = dir.list(getTemplateFilter());
		try {
			for (int i = 0; i < fileNames.length; i++) {
				if (files.length > i) {
					ITemplate tName = new Template(
							tConfig.getTemplate(fileNames[i]));
					ITemplate tContent = new Template(
							tConfig.getTemplate(files[i]));
					templates.add(new CompositeTemplate(tContent, tName));
					if (LOG.isDebugEnabled()) {
						LOG.debug("Template " + fileNames[i] + "/" + files[i]
								+ " added to the list");
					}
				} else {
					throw new TemplateRuntimeException(
							"There are more filenames templates than content templates");
				}
			}
		} catch (IOException e) {
			throw new TemplateRuntimeException(e);
		}

	}

	private FilenameFilter getTemplateFilenameFilter() {
		return new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return (name.indexOf("-name." + Template.MDA_EXTENSION) != -1)
						|| (name.indexOf("-name."
								+ Template.MDA_OVERWRITE_EXTENSION) != -1);
			}
		};
	}

	private FilenameFilter getTemplateFilter() {
		return new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return (name.indexOf("-content." + Template.MDA_EXTENSION) != -1)
						|| (name.indexOf("-content."
								+ Template.MDA_OVERWRITE_EXTENSION) != -1);
			}
		};
	}

}

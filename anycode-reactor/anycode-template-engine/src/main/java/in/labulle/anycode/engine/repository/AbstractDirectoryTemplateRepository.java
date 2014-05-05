package in.labulle.anycode.engine.repository;

import in.labulle.anycode.engine.core.ICodeGenerationArtifact;
import in.labulle.anycode.engine.core.IMacro;
import in.labulle.anycode.engine.core.ITemplate;
import in.labulle.anycode.engine.core.TemplateScope;
import in.labulle.anycode.engine.exception.TemplateRuntimeException;
import in.labulle.anycode.engine.service.handler.ClassifierCodeGenerator;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDirectoryTemplateRepository implements ITemplateRepository {
	/**
	 * log.
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractDirectoryTemplateRepository.class);
	
	private String path;
	private List<ICodeGenerationArtifact> codeGenerationArtifacts;

	public AbstractDirectoryTemplateRepository(final String path) {
		this.path = path;
	}

	public List<ICodeGenerationArtifact> getCodeGenerationArtifacts() {
		if (codeGenerationArtifacts == null) {
			codeGenerationArtifacts = new ArrayList<ICodeGenerationArtifact>();
			refresh();
		}
		return codeGenerationArtifacts;
	}

	public void refresh() {
		codeGenerationArtifacts.clear();
		File dir = new File(path);
		loadTemplates(dir, TemplateScope.CLASSIFIER);
		loadTemplates(dir, TemplateScope.MODEL);
		loadMacros(dir);
	}

	protected void loadTemplates(File dir, TemplateScope scope) {
		List<String> fileNames = Arrays.asList(dir.list(getTemplateFilenameFilter(scope)));
		List<String> files = Arrays.asList(dir.list(getTemplateFilter(scope)));
		Collections.sort(fileNames);
		Collections.sort(files);
		for (int i = 0; i < fileNames.size(); i++) {
			try {
				if (files.size() > i) {
					ITemplate t = buildTemplate(path + File.separator + fileNames.get(i), path + File.separator + files.get(i), scope);
					codeGenerationArtifacts.add(t);
					if(LOG.isDebugEnabled()) {
						LOG.debug("Template built : " + t);
					}
				} else {
					throw new TemplateRuntimeException("There are more filenames templates than content templates");
				}
			} catch (Exception e) {
				TemplateRuntimeException ee = new TemplateRuntimeException("Error parsing template " + fileNames.get(i), e);
				throw ee;
			}
		}
	}

	protected void loadMacros(File dir) {
		List<String> files = Arrays.asList(dir.list(getMacroFilter()));
		for (int i = 0; i < files.size(); i++) {
			IMacro m = buildMacro(path + File.separator + files.get(i));
			this.codeGenerationArtifacts.add(m);
		}
	}

	public static FilenameFilter getTemplateFilenameFilter(final TemplateScope scope) {
		return new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return (name.indexOf(ITemplate.NAME_SUFFIX + "." + scope.getExtension()) != -1);
			}
		};
	}

	public static FilenameFilter getTemplateFilter(final TemplateScope scope) {
		return new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return (name.indexOf(ITemplate.CONTENT_SUFFIX + "." + scope.getExtension()) != -1);
			}
		};
	}

	public static FilenameFilter getMacroFilter() {
		return new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return (name.indexOf(ITemplate.DIRECTIVE_SUFFIX + "." + ITemplate.MDA_OVERWRITE_EXTENSION) != -1);
			}
		};
	}

	protected abstract ITemplate buildTemplate(String nameTemplatePath, String contentTemplatePath, TemplateScope scope);

	protected abstract IMacro buildMacro(String macroFilePath);
}

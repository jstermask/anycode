package in.labulle.anycode.engine.repository;

import in.labulle.anycode.engine.core.ICodeGenerationArtifact;
import in.labulle.anycode.engine.core.ITemplate;
import in.labulle.anycode.engine.exception.TemplateRuntimeException;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDirectoryTemplateRepository implements ITemplateRepository {
	private String path;
	private List<ICodeGenerationArtifact> codeGenerationArtifacts;
	
	public AbstractDirectoryTemplateRepository(final String path) {
		this.path = path;
	}
	

	public List<ICodeGenerationArtifact> getCodeGenerationArtifacts() {
		if(codeGenerationArtifacts == null) {
			codeGenerationArtifacts = new ArrayList<ICodeGenerationArtifact>();
			refresh();
		}
		return codeGenerationArtifacts;
	}

	public void refresh() {
		codeGenerationArtifacts.clear();
		File dir = new File(path);
		 String fileNames[] = dir.list(getTemplateFilenameFilter());
	     String files[] = dir.list(getTemplateFilter());
	        try {
	            for (int i = 0; i < fileNames.length; i++) {
	                if (files.length > i) {
	                	ITemplate t = buildTemplate(path + File.separator + fileNames[i], path + File.separator + files[i]);
	                    codeGenerationArtifacts.add(t);
	                } else {
	                    throw new TemplateRuntimeException("There are more filenames templates than content templates");
	                }
	            }
	        } catch (Exception e) {
	            throw new TemplateRuntimeException(e);
	        }

	}
	
	public static FilenameFilter getTemplateFilenameFilter() {
		return new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return (name.indexOf("-name." + ITemplate.MDA_OVERWRITE_EXTENSION) != -1);
			}
		};
	}

	public static FilenameFilter getTemplateFilter() {
		return new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return (name.indexOf("-content." + ITemplate.MDA_OVERWRITE_EXTENSION) != -1);
			}
		};
	}
	
	protected abstract ITemplate buildTemplate(String nameTemplatePath, String contentTemplatePath);
}


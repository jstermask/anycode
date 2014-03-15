package in.labulle.anycode.engine.core;

import in.labulle.anycode.engine.exception.TemplateException;

import java.io.File;
import java.util.Map;


/**
 * Template interface.
 * 
 * @author José Carreno
 * 
 */
public interface ITemplate extends ICodeGenerationArtifact {
	public static final String MDA_OVERWRITE_EXTENSION = "mda";
	public static final String NAME_SUFFIX = "-name";
	public static final String CONTENT_SUFFIX = "-content";
	public static final String DIRECTIVE_SUFFIX = "-directive";

	/**
	 * 
	 * @return true if template can override an existing file
	 */
	boolean overrides();

	/**
	 * Renders template and return the result as string
	 * 
	 * @param context
	 *            parameters
	 * @return render result as string
	 * @throws TemplateRuntimeException
	 */
	String renderAsString(Map<String, Object> context)
			throws TemplateException;

	/**
	 * Renders template and writes the result in the given file.
	 * 
	 * @param context
	 *            parameters
	 * @param filePath
	 *            file to render the template to.
	 * @throws TemplateRuntimeException
	 */
	File renderToFile(Map<String, Object> context)
			throws TemplateException;
	
	

}

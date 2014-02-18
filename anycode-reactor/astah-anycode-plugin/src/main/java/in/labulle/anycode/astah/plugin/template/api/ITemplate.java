package in.labulle.anycode.astah.plugin.template.api;

import in.labulle.anycode.astah.plugin.template.exception.TemplateException;
import in.labulle.anycode.astah.plugin.template.exception.TemplateRuntimeException;

import java.io.File;
import java.util.Map;


/**
 * Template interface.
 * 
 * @author José Carreno
 * 
 */
public interface ITemplate extends ICodeGenerationArtifact {

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

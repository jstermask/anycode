package in.labulle.anycode.astah.plugin.generator.service;

import in.labulle.anycode.astah.plugin.report.ICodeGenerationLog;
import in.labulle.anycode.astah.plugin.template.ICodeGenerationArtifact;

import java.util.List;


/**
 * Code generation service
 * 
 * @author José Carreno
 * 
 */
public interface ICodeGenerationService {

	/**
	 * 
	 * @param templatePath
	 * @param outputPath
	 */
	void generateCode(final String templatePath, final String outputPath);
	
	/**
	 * 
	 * @param report
	 */
	public void attachReport(ICodeGenerationLog report);
	
	/**
	 * 
	 * @param templatePath templates' path
	 * @return internal macros
	 */
	List<ICodeGenerationArtifact> getCodeGenerationArtifacts(final String templatePath);
	
	/**
	 * 
	 * @return the directory were project is stored.
	 */
	public String getModelFilePath();
	
}

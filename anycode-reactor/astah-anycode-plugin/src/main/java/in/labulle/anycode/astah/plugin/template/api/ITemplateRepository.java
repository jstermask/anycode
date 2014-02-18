package in.labulle.anycode.astah.plugin.template.api;

import java.util.List;

/**
 * Returns templates
 * 
 * @author José Carreno
 * 
 */
public interface ITemplateRepository {
	/**
	 * 
	 * @return available templates
	 */
	List<ICodeGenerationArtifact> getCodeGenerationArtifacts();

	/**
	 * refreshes templates and macros
	 */
	void refresh();
}

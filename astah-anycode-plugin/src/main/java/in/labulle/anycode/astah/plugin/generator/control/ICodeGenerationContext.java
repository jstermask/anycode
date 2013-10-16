package in.labulle.anycode.astah.plugin.generator.control;

import in.labulle.anycode.astah.plugin.mvc.IContext;
import in.labulle.anycode.astah.plugin.report.ICodeGenerationLog;

public interface ICodeGenerationContext extends IContext {
	String getTemplatePath();
	String getOutputPath();
	ICodeGenerationLog getLog();
	public void setTemplatesAndMacrosCount(Integer macros, Integer templates);
}

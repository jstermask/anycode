package in.labulle.anycode.astah.plugin.report;

import java.io.File;

public interface ICodeGenerationEvent {	
	
	GenerationStatus getStatus();
	
	String getTemplateName();
	
	String getUmlElementName();
	
	String getDetails();
	
	Exception getFailureCause();
	
	File getGeneratedFile();
	
	
	
}

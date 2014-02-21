package in.labulle.anycode.engine.log;

import java.io.File;

public interface ICodeGenerationEvent {	
	
	GenerationStatus getStatus();
	
	String getTemplateName();
	
	String getUmlElementName();
	
	String getDetails();
	
	Exception getFailureCause();
	
	File getGeneratedFile();
	
	
	
}

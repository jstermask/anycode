package in.labulle.anycode.editor.persistence;

import in.labulle.anycode.editor.core.Directive;
import in.labulle.anycode.editor.core.Function;
import in.labulle.anycode.editor.core.Macro;
import in.labulle.anycode.editor.core.Param;

public interface IDirectiveDao {
	Macro newMacro();
	Param newParam();
	Function newFunction();
	Directive newDirective();
	
	void saveToFile(Directive d, String outputPath);
	
	Directive loadFromFile(String path);
}

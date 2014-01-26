package in.labulle.anycode.editor.context;

import java.io.File;

import in.labulle.anycode.editor.core.Directive;

public interface IDirectiveContext {
	Directive getDirective();
	void setDirective(Directive d);
	void setFile(File f);
	File getFile();
}

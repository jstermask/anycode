package in.labulle.anycode.editor.facade;

import in.labulle.anycode.editor.context.IDirectiveContext;
import in.labulle.anycode.editor.core.Directive;

import java.io.File;
import java.util.List;

public interface IDirectiveFacade {
	IDirectiveContext loadFromSelectedFile(final File selectedFile);
	void saveCurrentDirective(final File selectedFile);
	Directive getCurrentDirective();
	List<Directive> getAllOpenedDirectives();
}

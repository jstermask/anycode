package in.labulle.anycode.editor.facade;

import in.labulle.anycode.editor.core.Directive;

import java.io.File;
import java.util.List;

public interface IDirectiveFacade {
	void loadFromSelectedFile(final File selectedFile);
	void saveCurrentDirective(final File selectedFile);
	Directive getCurrentDirective();
	List<Directive> getAllOpenedDirectives();
}

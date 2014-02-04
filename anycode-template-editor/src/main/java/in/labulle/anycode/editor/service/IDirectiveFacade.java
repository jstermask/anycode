package in.labulle.anycode.editor.service;

import in.labulle.anycode.editor.core.Directive;

import java.io.File;

public interface IDirectiveFacade {
	void loadFromSelectedFile(final File selectedFile);
	void saveCurrentDirective(final File selectedFile);
	Directive getCurrentDirective();
}

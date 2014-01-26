package in.labulle.anycode.editor.controller;

import in.labulle.anycode.editor.core.Directive;

import java.io.File;

public interface IDirectiveFileController {
	void loadFromSelectedFile(final File selectedFile);
	void saveCurrentDirective(final File selectedFile);
	Directive getCurrentDirective();
}

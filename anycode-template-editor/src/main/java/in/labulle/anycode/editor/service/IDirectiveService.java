package in.labulle.anycode.editor.service;

import in.labulle.anycode.editor.core.Directive;

public interface IDirectiveService {
	Directive newDirective();
	void saveToFile(Directive d, String outputPath);
	Directive loadFromFile(String path);
}

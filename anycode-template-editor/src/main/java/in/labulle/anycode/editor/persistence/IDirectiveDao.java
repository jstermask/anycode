package in.labulle.anycode.editor.persistence;

import in.labulle.anycode.editor.core.Directive;

public interface IDirectiveDao {
	Directive newDirective();

	void saveToFile(Directive d, String outputPath);

	Directive loadFromFile(String path);
}

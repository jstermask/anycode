package in.labulle.anycode.editor.context;

import java.io.File;

import in.labulle.anycode.editor.core.Directive;

public class DirectiveContext implements IDirectiveContext {
	private File file;
	
	private Directive directive;

	public Directive getDirective() {
		return directive;
	}

	public void setDirective(Directive directive) {
		this.directive = directive;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}

}

package in.labulle.anycode.editor.context;

import in.labulle.anycode.editor.core.Directive;

import java.io.File;

public class DirectiveContext implements IDirectiveContext {
	private IEditorContext editorContext;

	protected DirectiveContext(final IEditorContext editorContext) {
		this.editorContext = editorContext;
	}

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

	@Override
	public void close() {
		editorContext.removeDirectiveContext(this);
	}

}

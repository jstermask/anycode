package in.labulle.anycode.editor.facade;

import in.labulle.anycode.editor.context.IDirectiveContext;
import in.labulle.anycode.editor.context.IEditorContext;
import in.labulle.anycode.editor.core.Directive;
import in.labulle.anycode.editor.service.IDirectiveService;

import java.io.File;
import java.util.List;

public class DirectiveFacade implements IDirectiveFacade {
	private final IEditorContext context;

	private IDirectiveService directiveService;

	public DirectiveFacade(IEditorContext context,
			IDirectiveService directiveService) {
		this.context = context;
		this.directiveService = directiveService;
	}

	@Override
	public IDirectiveContext loadFromSelectedFile(File selectedFile) {
		try {
			Directive d = getDirectiveService().loadFromFile(
					selectedFile.getAbsolutePath());
			IDirectiveContext ctx = this.context.newDirectiveContext();
			ctx.setDirective(d);
			ctx.setFile(selectedFile);
			return ctx;
		} catch (RuntimeException e) {
			return null;

		}

	}

	@Override
	public void saveCurrentDirective(File selectedFile) {
		getDirectiveService().saveToFile(getCurrentDirective(),
				selectedFile.getAbsolutePath());
	}

	@Override
	public Directive getCurrentDirective() {
		return context.getActiveDirectiveContext().getDirective();
	}

	private IDirectiveService getDirectiveService() {
		return directiveService;
	}

	@Override
	public List<Directive> getAllOpenedDirectives() {
		// TODO Auto-generated method stub
		return null;
	}

}

package in.labulle.anycode.editor.facade;

import in.labulle.anycode.editor.context.IDirectiveContext;
import in.labulle.anycode.editor.context.IEditorContext;
import in.labulle.anycode.editor.core.Directive;
import in.labulle.anycode.editor.persistence.IDirectiveDao;

import java.io.File;
import java.util.List;

public class DirectiveFacade implements IDirectiveFacade {
	private final IEditorContext context;
	
	private IDirectiveDao directiveDao;

	public DirectiveFacade(IEditorContext context, IDirectiveDao directiveDao) {
		this.context = context;
		this.directiveDao = directiveDao;
	}

	@Override
	public void loadFromSelectedFile(File selectedFile) {
		Directive d = directiveDao.loadFromFile(selectedFile.getAbsolutePath());
		IDirectiveContext ctx = this.context.newDirectiveContext();
		ctx.setDirective(d);
		ctx.setFile(selectedFile);
	}

	@Override
	public void saveCurrentDirective(File selectedFile) {
		directiveDao.saveToFile(getCurrentDirective(), selectedFile.getAbsolutePath());
	}

	@Override
	public Directive getCurrentDirective() {
		return context.getActiveDirectiveContext().getDirective();
	}

	public IDirectiveDao getDirectiveDao() {
		return directiveDao;
	}

	public void setDirectiveDao(IDirectiveDao directiveDao) {
		this.directiveDao = directiveDao;
	}

	@Override
	public List<Directive> getAllOpenedDirectives() {
		// TODO Auto-generated method stub
		return null;
	}

}

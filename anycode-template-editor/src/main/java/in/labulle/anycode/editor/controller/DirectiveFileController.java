package in.labulle.anycode.editor.controller;

import in.labulle.anycode.editor.context.IDirectiveContext;
import in.labulle.anycode.editor.core.Directive;
import in.labulle.anycode.editor.persistence.IDirectiveDao;

import java.io.File;

public class DirectiveFileController implements IDirectiveFileController {
	private final IDirectiveContext context;
	
	private IDirectiveDao directiveDao;

	public DirectiveFileController(IDirectiveContext context) {
		this.context = context;
	}

	@Override
	public void loadFromSelectedFile(File selectedFile) {
		Directive d = directiveDao.loadFromFile(selectedFile.getAbsolutePath());
		context.setDirective(d);
		context.setFile(selectedFile);
	}

	@Override
	public void saveCurrentDirective(File selectedFile) {
		directiveDao.saveToFile(context.getDirective(), selectedFile.getAbsolutePath());
	}

	@Override
	public Directive getCurrentDirective() {
		return context.getDirective();
	}

	public IDirectiveDao getDirectiveDao() {
		return directiveDao;
	}

	public void setDirectiveDao(IDirectiveDao directiveDao) {
		this.directiveDao = directiveDao;
	}

}

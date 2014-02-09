package in.labulle.anycode.editor.service.impl;

import in.labulle.anycode.editor.core.Directive;
import in.labulle.anycode.editor.persistence.IDirectiveDao;
import in.labulle.anycode.editor.service.IDirectiveService;

public class DirectiveService implements IDirectiveService {
	private IDirectiveDao directiveDao;
	
	public DirectiveService(IDirectiveDao dao) {
		this.directiveDao = dao;
	}

	@Override
	public Directive newDirective() {
		return getDirectiveDao().newDirective();
	}

	@Override
	public void saveToFile(Directive d, String outputPath) {
		getDirectiveDao().saveToFile(d, outputPath);

	}

	@Override
	public Directive loadFromFile(String path) {
		return getDirectiveDao().loadFromFile(path);
	}
	
	private IDirectiveDao getDirectiveDao() {
		return directiveDao;
	}

}

package in.labulle.anycode.editor.ux;

import in.labulle.anycode.editor.context.EditorContext;
import in.labulle.anycode.editor.facade.DirectiveFacade;
import in.labulle.anycode.editor.facade.IDirectiveFacade;
import in.labulle.anycode.editor.persistence.xml.XmlDirectiveDao;
import in.labulle.anycode.editor.service.impl.DirectiveService;
import javafx.fxml.FXML;

public class ApplicationController {
	@FXML
	private MenuController menuController;
	

	
	private IDirectiveFacade directiveFacade;
	
	
	public ApplicationController() {
		this.directiveFacade = new DirectiveFacade(new EditorContext(), new DirectiveService(new XmlDirectiveDao()));
	}

	public MenuController getMenuController() {
		return menuController;
	}

	public void setMenuController(MenuController menuController) {
		this.menuController = menuController;
	}
	
	
	public IDirectiveFacade getDirectiveFacade() {
		return directiveFacade;
	}
}

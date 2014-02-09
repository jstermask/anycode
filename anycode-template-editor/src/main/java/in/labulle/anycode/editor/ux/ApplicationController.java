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
	
	@FXML
	private TabController tabController;
	
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
	
	public TabController getTabController() {
		return tabController;
	}
	
	public void setTabController(TabController tabController) {
		this.tabController = tabController;
	}
	
	public IDirectiveFacade getDirectiveFacade() {
		return directiveFacade;
	}
}

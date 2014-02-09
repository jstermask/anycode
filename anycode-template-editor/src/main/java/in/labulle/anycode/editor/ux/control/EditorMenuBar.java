package in.labulle.anycode.editor.ux.control;

import javafx.fxml.FXML;
import in.labulle.anycode.editor.ux.MenuController;



public class EditorMenuBar extends javafx.scene.control.MenuBar {

	@FXML
	private MenuController menuController;
	
	public MenuController getMenuController() {
		return menuController;
	}
}

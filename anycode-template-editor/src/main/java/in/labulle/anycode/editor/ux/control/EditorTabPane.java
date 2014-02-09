package in.labulle.anycode.editor.ux.control;

import in.labulle.anycode.editor.context.IDirectiveContext;
import in.labulle.anycode.editor.ux.TabController;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class EditorTabPane extends TabPane {
	@FXML
	private TabController tabController;
	
	public TabController getTabController() {
		return tabController;
	}
	
	
	public void loadDirective(IDirectiveContext context) {
		Tab t = new EditorTab(context);
		getTabs().add(t);
	}
}

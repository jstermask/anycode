package in.labulle.anycode.editor.ux.control;

import in.labulle.anycode.editor.context.IDirectiveContext;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class EditorTabPane extends TabPane {

	
	
	public void loadDirective(IDirectiveContext context) {
		Tab t = new EditorTab(context);
		getTabs().add(t);
	}
}

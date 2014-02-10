package in.labulle.anycode.editor.ux;

import in.labulle.anycode.editor.context.IDirectiveContext;
import in.labulle.anycode.editor.facade.IDirectiveFacade;
import in.labulle.anycode.editor.ux.control.EditorTabPane;
import in.labulle.anycode.editor.ux.control.TemplateEditorScene;
import in.labulle.anycode.editor.ux.dialog.Dialogs;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuController {
	private static final Logger LOG = LoggerFactory
			.getLogger(MenuController.class);

	@FXML
	private VBox menuPanel;

	@FXML
	protected void handleOpen(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File chosenFile = fileChooser.showOpenDialog(getTabView().getScene()
				.getWindow());
		if (chosenFile != null) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("Opened file " + chosenFile.getAbsolutePath());
			}

			IDirectiveContext ctx = getDirectiveFacade().loadFromSelectedFile(chosenFile);
			if(ctx == null) {
				Dialogs.showError("Selected file is not valid !");
			} else {
				getTabView().loadDirective(ctx);
			}
			
		}

	}

	private IDirectiveFacade getDirectiveFacade() {
		return ((TemplateEditorScene) (menuPanel.getScene()))
				.getDirectiveFacade();
	}

	private EditorTabPane getTabView() {
		return (EditorTabPane) menuPanel.getParent().lookup("#tabView");
	}
	
	
	

}

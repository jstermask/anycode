package in.labulle.anycode.editor.ux;

import in.labulle.anycode.editor.facade.IDirectiveFacade;

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

	private IDirectiveFacade directiveFacade;

	private @FXML
	VBox mainPanel;

	@FXML
	protected void handleOpen(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File chosenFile = fileChooser.showOpenDialog(getMainPanel().getScene()
				.getWindow());
		if (LOG.isDebugEnabled()) {
			if (chosenFile != null) {
				LOG.debug("Opened file " + chosenFile.getAbsolutePath());
			} else {
				LOG.debug("No opened file");
			}
		}

	}

	private IDirectiveFacade getDirectiveFacade() {
		return directiveFacade;
	}

	private VBox getMainPanel() {
		return mainPanel;
	}

}

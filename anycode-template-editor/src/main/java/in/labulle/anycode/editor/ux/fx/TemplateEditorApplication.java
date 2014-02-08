package in.labulle.anycode.editor.ux.fx;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TemplateEditorApplication extends Application {
	private static final Logger LOG = LoggerFactory.getLogger(TemplateEditorApplication.class);
	

	public static void main(String[] args) throws Exception {
		launch(args);
		if(LOG.isDebugEnabled()) {
			LOG.debug("Template editor started");
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("main_screen.fxml"));
        Scene scene = new Scene(root, 300, 275);
        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
	}

}

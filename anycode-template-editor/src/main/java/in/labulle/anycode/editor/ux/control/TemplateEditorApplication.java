package in.labulle.anycode.editor.ux.control;


import in.labulle.anycode.editor.context.EditorContext;
import in.labulle.anycode.editor.facade.DirectiveFacade;
import in.labulle.anycode.editor.facade.IDirectiveFacade;
import in.labulle.anycode.editor.persistence.xml.XmlDirectiveDao;
import in.labulle.anycode.editor.service.impl.DirectiveService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        Scene scene = new TemplateEditorScene(getFacadeInstance(), root, 700, 480);
        primaryStage.setTitle("any<code/> Directive Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public TemplateEditorApplication() {
		super();
	}
	
	private IDirectiveFacade getFacadeInstance() {
		return new DirectiveFacade(new EditorContext(), new DirectiveService(new XmlDirectiveDao()));
	}
	


}

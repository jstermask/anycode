package in.labulle.anycode.editor.ux.control;

import in.labulle.anycode.editor.facade.IDirectiveFacade;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class TemplateEditorScene extends Scene {
	private IDirectiveFacade directiveFacade;
	

	public TemplateEditorScene(IDirectiveFacade facade, Parent parent, double width, double height) {
		super(parent, width, height);
		this.directiveFacade = facade;
	}
	
	public IDirectiveFacade getDirectiveFacade() {
		return directiveFacade;
	}

}

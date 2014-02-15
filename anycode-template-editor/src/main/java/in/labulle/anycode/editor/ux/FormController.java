package in.labulle.anycode.editor.ux;

import in.labulle.anycode.editor.ux.bean.ApiElementBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FormController {
	
	@FXML
	private TextField name;
	
	@FXML
	private TextArea description;
	
	@FXML
	private TextArea code;

	@FXML
	public void saveCurrentElement(ActionEvent evt) {
		save();
		name.getParent().setVisible(false);
	}
	
	private ApiElementBean element;
	


	


	public void setElement(ApiElementBean element) {
		this.element = element;
		updateForm();
	}

	public void save() {
		element.updateModel();
	}
	
	
	private void updateForm() {
		element.nameProperty().bindBidirectional(name.textProperty());
		element.codeProperty().bindBidirectional(code.textProperty());
		element.descriptionProperty().bindBidirectional(description.textProperty());
	}

}

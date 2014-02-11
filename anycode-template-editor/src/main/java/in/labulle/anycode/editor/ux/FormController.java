package in.labulle.anycode.editor.ux;

import in.labulle.anycode.editor.core.ApiElement;
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
	
	private ApiElement element;
	


	


	public void setElement(ApiElement element) {
		this.element = element;
		updateForm();
	}

	public void save() {
		updateModel();
	}
	


	private void updateModel() {
		element.setName(name.getText());
		element.setDescription(description.getText());
		element.setCode(code.getText());
	}
	
	private void updateForm() {
		name.setText(element.getName());
		description.setText(element.getDescription());
		code.setText(element.getCode());
	}

}

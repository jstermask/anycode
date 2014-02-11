package in.labulle.anycode.editor.ux.control;

import in.labulle.anycode.editor.core.ApiElement;
import javafx.scene.layout.VBox;

public class ApiElementForm extends VBox {
	private ApiElement element;
	
	public void setElement(ApiElement element) {
		this.element = element;
	}
	
	public ApiElement getElement() {
		return element;
	}
}

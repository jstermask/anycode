package in.labulle.anycode.editor.ux.bean;

import in.labulle.anycode.editor.core.ApiElement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ApiElementBean {
	private ApiElement element;
	
	private StringProperty name = new SimpleStringProperty(this, "name");
	private StringProperty code = new SimpleStringProperty(this, "code");
	private StringProperty description = new SimpleStringProperty(this, "description");
	
	public ApiElementBean() {

	}
	
	public void setElement(ApiElement model) {
		this.element = model;
		setCode(element.getCode());
		setDescription(element.getDescription());
		setName(element.getName());
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	
	public StringProperty codeProperty() {
		return code;
	}
	
	public StringProperty descriptionProperty() {
		return description;
	}

	
	public String getCode() {
		return code.get();
	}
	
	public void setCode(String code) {
		this.code.set(code);
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getDescription() {
		return description.get();
	}
	
	public void setDescription(String description) {
		this.description.set(description);
	}
	
	public void updateModel() {
		element.setCode(getCode());
		element.setDescription(getDescription());
		element.setName(getName());
	}
}

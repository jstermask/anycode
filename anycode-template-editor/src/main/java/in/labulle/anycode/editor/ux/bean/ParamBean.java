package in.labulle.anycode.editor.ux.bean;

import in.labulle.anycode.editor.core.Param;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ParamBean {
	private Param param;
	
	public ParamBean(Param p) {
		setParam(p);
	}
	
	public final void setParam(Param param) {
		this.param = param;
		setName(param.getName());
		setDescription(param.getDescription());
	}
	
	public Param getParam() {
		return param;
	}
	
	private StringProperty name = new SimpleStringProperty(this, "name");

	private StringProperty description = new SimpleStringProperty(this, "description");

	public StringProperty nameProperty() {
		return name;
	}

	public StringProperty descriptionProperty() {
		return description;
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
		param.setDescription(getDescription());
		param.setName(getName());
	}
}

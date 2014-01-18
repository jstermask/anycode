package in.labulle.anycode.editor.core;

import java.util.ArrayList;
import java.util.List;

public abstract class ApiElement {
	private String description;
	private String name;
	private String code;
	private final List<Param> params = new ArrayList<>();
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Param> getParams() {
		return params;
	}

}

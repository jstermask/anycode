package in.labulle.anycode.editor.core;

public class Param {
	private String name;
	private String description;
	
	protected Param() {
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

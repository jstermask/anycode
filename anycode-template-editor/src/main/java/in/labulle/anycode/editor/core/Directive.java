package in.labulle.anycode.editor.core;

import java.util.ArrayList;
import java.util.List;

public class Directive {
	
	
	protected Directive() {

	}

	private final List<ApiElement> elements = new ArrayList<ApiElement>();
	
	private String description;

	public List<ApiElement> getElements() {
		return elements;
	}

	public final Function newFunction() {
		Function f = getNewFunctionIntance();
		elements.add(f);
		return f;
	}

	protected Function getNewFunctionIntance() {
		return new Function();
	}

	public final Macro newMacro() {
		Macro f = getNewMacroIntance();
		elements.add(f);
		return f;
	}

	protected Macro getNewMacroIntance() {
		return new Macro();
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}

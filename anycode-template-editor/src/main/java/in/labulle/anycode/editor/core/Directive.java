package in.labulle.anycode.editor.core;

import java.util.ArrayList;
import java.util.List;

public class Directive {
	protected Directive() {
		
	}
	
	private final List<ApiElement> elements = new ArrayList<>();

	public List<ApiElement> getElements() {
		return elements;
	}
}

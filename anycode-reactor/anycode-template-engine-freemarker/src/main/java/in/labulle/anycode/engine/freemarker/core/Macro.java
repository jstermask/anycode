package in.labulle.anycode.engine.freemarker.core;

import in.labulle.anycode.engine.core.IMacro;

public class Macro implements IMacro {
	private String name;
	private String prefix;
	private String label;
	
	public Macro(final String name, final String prefix, final String label) {
		super();
		this.name = name;
		this.prefix = prefix;
		this.label = label;
	}

	public String getVarName() {
		return this.name;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public String getName() {
		return this.label;
	}

	public Object getInstance() {
		return this;
	}
}

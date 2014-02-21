package in.labulle.anycode.astah.plugin.template.freemarker.core;

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

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getPrefix() {
		return this.prefix;
	}

	@Override
	public String getLabel() {
		return this.label;
	}
}

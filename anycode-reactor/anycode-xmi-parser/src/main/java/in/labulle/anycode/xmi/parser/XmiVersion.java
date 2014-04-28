package in.labulle.anycode.xmi.parser;

public enum XmiVersion {
	UML211("http://schema.omg.org/spec/UML/2.1.1", "http://schema.omg.org/spec/XMI/2.1"),
	UML212("http://schema.omg.org/spec/UML/2.1.2", "http://schema.omg.org/spec/XMI/2.1"),
	UML22("http://schema.omg.org/spec/UML/2.2", "http://schema.omg.org/spec/XMI/2.1"),
	UML23("http://www.omg.org/spec/UML/20090901", "http://schema.omg.org/spec/XMI/2.1"),
	UML241("http://www.omg.org/spec/UML/20100901", "http://schema.omg.org/spec/XMI/2.1");

	private String umlNamespace;

	private String xmiNamespace;

	private XmiVersion(final String umlNs, final String xmiNs) {
		this.umlNamespace = umlNs;
		this.xmiNamespace = xmiNs;
	}

	public String getUmlNamespace() {
		return umlNamespace;
	}

	public String getXmiNamespace() {
		return xmiNamespace;
	}
}

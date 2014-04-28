package in.labulle.anycode.xmi.core;

public enum UmlVersion {
	UML_2_1_1("http://schema.omg.org/spec/UML/2.1.1"),
	UML_2_1_2("http://schema.omg.org/spec/UML/2.1.2"),
	UML_2_2("http://schema.omg.org/spec/UML/2.2"),
	UML_2_3("http://www.omg.org/spec/UML/20090901"),
	UML_2_4_1("http://www.omg.org/spec/UML/20100901");

	private String namespace;

	private UmlVersion(final String umlNs) {
		this.namespace = umlNs;
	}

	public String getNamespace() {
		return namespace;
	}
}
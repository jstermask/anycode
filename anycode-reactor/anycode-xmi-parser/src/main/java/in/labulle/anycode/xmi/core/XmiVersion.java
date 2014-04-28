package in.labulle.anycode.xmi.core;

public enum XmiVersion {
	XMI_2_1("http://schema.omg.org/spec/XMI/2.1");

	private String namespace;


	private XmiVersion(final String xmiNs) {
		this.namespace = xmiNs;
	}

	public String getNamespace() {
		return namespace;
	}
}

package in.labulle.anycode.engine.core;

public enum TemplateScope {
	CLASSIFIER("mda"), MODEL("mdm");
	
	private String extension;
	
	private TemplateScope(final String ext) {
		this.extension = ext;
	}
	
	public String getExtension() {
		return extension;
	}
}

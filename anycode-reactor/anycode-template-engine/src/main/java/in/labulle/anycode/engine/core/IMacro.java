package in.labulle.anycode.engine.core;

public interface IMacro extends ICodeGenerationArtifact {
	public static final String MDM_EXTENSION = "mdm";
	String getVarName();
	Object getInstance();
	
}

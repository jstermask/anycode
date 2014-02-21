package in.labulle.anycode.engine.exception;


public class NoTemplateFoundException extends TemplateRuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4306420861634200185L;


	public NoTemplateFoundException() {
		super("No template found in the given template directory");
	}


}

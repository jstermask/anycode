package in.labulle.anycode.engine.exception;

public class TemplateRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4576232351997682156L;

	public TemplateRuntimeException(Throwable cause) {
		super(cause);
	}

	public TemplateRuntimeException(String message) {
		super(new RuntimeException(message));
	}

	public TemplateRuntimeException(String message, Throwable cause) {
		super(new RuntimeException(message, cause));
	}

}

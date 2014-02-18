package in.labulle.anycode.astah.plugin.template.exception;

import in.labulle.anycode.astah.plugin.exception.AnycodeRuntimeException;

public class TemplateRuntimeException extends AnycodeRuntimeException {

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

}

package in.labulle.anycode.engine.exception;

import in.labulle.anycode.engine.core.ITemplate;


public class TemplateRenderingException extends TemplateException {
	/**
	 * uid.
	 */
	private static final long serialVersionUID = -4306420861634200125L;

	private final String classToRender;

	private final ITemplate template;

	public TemplateRenderingException(Throwable cause, String className,
			ITemplate template) {
		super(cause);
		this.classToRender = className;
		this.template = template;
	}

	@Override
	public String getMessage() {
		return "Error processing class " + classToRender
				+ " with template " + template;
	}

}

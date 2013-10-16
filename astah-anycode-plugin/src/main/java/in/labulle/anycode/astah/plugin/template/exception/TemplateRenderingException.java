package in.labulle.anycode.astah.plugin.template.exception;

import in.labulle.anycode.astah.plugin.template.ITemplate;

import com.change_vision.jude.api.inf.model.IClass;


public class TemplateRenderingException extends TemplateException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4306420861634200125L;

	private final IClass classToRender;

	private final ITemplate template;

	public TemplateRenderingException(Throwable cause, IClass classToRender,
			ITemplate template) {
		super(cause);
		this.classToRender = classToRender;
		this.template = template;
	}

	@Override
	public String getMessage() {
		return "Error processing class " + ((classToRender != null) ? classToRender.getName() : "null")
				+ " with template " + template;
	}

}

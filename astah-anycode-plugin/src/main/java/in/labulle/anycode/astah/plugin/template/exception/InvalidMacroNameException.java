package in.labulle.anycode.astah.plugin.template.exception;

import in.labulle.anycode.astah.plugin.exception.AnycodeException;


public class InvalidMacroNameException extends AnycodeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2047307786852402845L;
	private final String filename;

	public InvalidMacroNameException(String fileName) {
		super(null);
		this.filename = fileName;
	}

	@Override
	public String getMessage() {
		return filename +" is not a valid macro name. [namespace]-[name].mdm expected";
	}



}

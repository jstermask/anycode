package in.labulle.anycode.engine;

import java.util.Map;

public interface ITemplate {
	public static final String TARGET_DIR_PARAM = "targetDir";
	
	void setNameTemplate(final String namepath);
	void setContentTemplate(final String contentpath);
	
	void render(Map<String, Object> context) throws TemplateException;
}

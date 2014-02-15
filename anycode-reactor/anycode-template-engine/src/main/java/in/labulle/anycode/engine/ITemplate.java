package in.labulle.anycode.engine;

import java.util.Map;

public interface ITemplate {
	public static final String TARGET_DIR_PARAM = "targetDir";
	
	void render(Map<String, Object> context) throws TemplateException;
}

package in.labulle.anycode.engine;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class GroovyTemplateTest {

	@Test
	public void test() throws TemplateException {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put(ITemplate.TARGET_DIR_PARAM, getClass().getResource("/").getFile());
		context.put("name", "MyClass");	
		String nameTpl = getClass().getResource("/tpl-java-class-name.mde").getFile();
		String contentTpl = getClass().getResource("/tpl-java-class-content.mde").getFile();
		GroovyTemplate gv = new GroovyTemplate(nameTpl, contentTpl);
		gv.render(context);
	}

}

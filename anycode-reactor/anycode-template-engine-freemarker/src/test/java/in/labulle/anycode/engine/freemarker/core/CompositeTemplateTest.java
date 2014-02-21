package in.labulle.anycode.astah.plugin.template;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import in.labulle.anycode.astah.plugin.template.config.Configuration;
import in.labulle.anycode.astah.plugin.template.freemarker.core.CompositeTemplate;
import in.labulle.anycode.astah.plugin.template.freemarker.core.Template;
import in.labulle.anycode.engine.exception.TemplateException;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Map;

import org.junit.Test;

public class CompositeTemplateTest extends TemplateTestBase {

	@Test
	public void test() throws URISyntaxException, TemplateException {
		Configuration.getConfiguration().put("c", null);
		Configuration.getConfiguration().put("packageName", "org.jjb.megatest");
		Configuration.getConfiguration().put("className", "AClass");
		Configuration.getConfiguration().put("targetDir",
				System.getProperty("java.io.tmpdir"));
		Map<String, Object> context = Configuration.getConfiguration();
		Template tName = getTemplate("/003test-name.mda");
		Template tContent = getTemplate("/003test-content.mda");
		CompositeTemplate tp = new CompositeTemplate(tContent, tName);

		tp.renderToFile(context);
		File expected = new File(context.get("targetDir")
				+ "/org/jjb/megatest/AClass.java");
		assertTrue(expected.exists());
		expected.delete();
		assertFalse(expected.exists());
	}

}

package in.labulle.anycode.astah.plugin.template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import in.labulle.anycode.astah.plugin.template.exception.TemplateException;
import in.labulle.anycode.astah.plugin.template.exception.TemplateRuntimeException;
import in.labulle.anycode.astah.plugin.template.freemarker.core.Template;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TemplateTest extends TemplateTestBase {

	@Test
	public void testOverrides() {
		Template t = getTemplate("001test-content.mda");
		Assert.assertTrue(t.overrides());
		t = getTemplate("004test-content.mdc");
		Assert.assertFalse(t.overrides());
	}

	@Test
	public void testRender() throws URISyntaxException, TemplateException {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("how", "well");
		Template t = getTemplate("001test-content.mda");
		String result = t.renderAsString(context);
		assertEquals("This template is working well.", result);
	}

	@Test
	public void testRenderEmpty() throws TemplateException {
		Map<String, Object> context = new HashMap<String, Object>();
		// context.put("how", "well");
		Template t = getTemplate("002test-content.mda");
		String result;
		result = t.renderAsString(context);
		assertNull(result);
	}

	@Test(expected = TemplateException.class)
	public void testRenderFail() throws TemplateException {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("c", null);
		// context.put("how", "well");
		Template t = getTemplate("001test-content.mda");
		t.renderAsString(context);
	}

}

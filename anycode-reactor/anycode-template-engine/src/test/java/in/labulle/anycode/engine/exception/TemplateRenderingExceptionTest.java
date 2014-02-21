package in.labulle.anycode.engine.exception;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import in.labulle.anycode.engine.core.ITemplate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
@RunWith(MockitoJUnitRunner.class)
public class TemplateRenderingExceptionTest {
	@Mock
	private ITemplate tpl;

	@Test
	public void testGetMessage() {
		when(tpl.toString()).thenReturn("TestTpl");
		TemplateRenderingException tr = new TemplateRenderingException(new NullPointerException(), "MyClass", tpl);			
		assertEquals("Error processing class MyClass with template TestTpl", tr.getMessage());
	}
	
	@Test
	public void testGetMessageClassNull() {
		when(tpl.toString()).thenReturn("TestTpl");
		TemplateRenderingException tr = new TemplateRenderingException(new NullPointerException(), null, tpl);
		assertEquals("Error processing class null with template TestTpl", tr.getMessage());
	}

}

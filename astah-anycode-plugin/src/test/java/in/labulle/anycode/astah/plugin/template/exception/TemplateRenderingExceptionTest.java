package in.labulle.anycode.astah.plugin.template.exception;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import in.labulle.anycode.astah.api.ClassMock;
import in.labulle.anycode.astah.plugin.template.api.ITemplate;

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
		TemplateRenderingException tr = new TemplateRenderingException(new NullPointerException(), new ClassMock("test", "MyClass"), tpl);			
		assertEquals("Error processing class MyClass with template TestTpl", tr.getMessage());
	}
	
	@Test
	public void testGetMessageClassNull() {
		when(tpl.toString()).thenReturn("TestTpl");
		TemplateRenderingException tr = new TemplateRenderingException(new NullPointerException(), null, tpl);
		assertEquals("Error processing class null with template TestTpl", tr.getMessage());
	}

}

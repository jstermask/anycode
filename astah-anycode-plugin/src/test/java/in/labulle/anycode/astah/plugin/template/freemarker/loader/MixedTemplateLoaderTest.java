package in.labulle.anycode.astah.plugin.template.freemarker.loader;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MixedTemplateLoaderTest {
	@Mock
	private ITemplateLoader tplLoader;
	
	@Before
	public void initMock() throws IOException {
		reset(tplLoader);
	}

	@Test
	public void testFind() throws IOException {
		when(tplLoader.findTemplateSource("test")).thenReturn(new String("FOUND"));
		MixedTemplateLoader ld = new MixedTemplateLoader(
				new ITemplateLoader[] { tplLoader });
		String aString = (String) ld.findTemplateSource("test");
		assertEquals("FOUND", aString);
	}
	
	@Test
	public void testGetLastModified() {
		when(tplLoader.getLastModified("test")).thenReturn(135468L);
		MixedTemplateLoader ld = new MixedTemplateLoader(
				new ITemplateLoader[] { tplLoader });
		assertEquals(135468L, ld.getLastModified("test"));
	}

}

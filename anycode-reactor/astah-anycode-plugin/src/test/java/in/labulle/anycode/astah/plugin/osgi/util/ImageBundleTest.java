package in.labulle.anycode.astah.plugin.osgi.util;

import static org.junit.Assert.*;
import in.labulle.anycode.engine.osgi.ImageBundle;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
@RunWith(MockitoJUnitRunner.class)
public class ImageBundleTest {

	@Test
	public void testTheme() throws MalformedURLException {
		String theme = "MyTheme";
		URL u = new URL("file://test/path/where/MyTheme/img.gif");
		URL uu = new URL("file://test/path/where/MyThemeOhoh/cool.gif");
	
		ImageBundle bdl = ImageBundle.initBundle(theme);
		bdl.addImage(u);
		bdl.addImage(uu);
		URL u2 = ImageBundle.getBundle().getImageURL("img.gif");
		URL uu2 = ImageBundle.getBundle().getImageURL("cool.gif");
		assertEquals(u, u2);
		assertNull(uu2);
	}

}

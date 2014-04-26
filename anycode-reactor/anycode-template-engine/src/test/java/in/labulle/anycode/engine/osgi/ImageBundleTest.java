package in.labulle.anycode.engine.osgi;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.Arrays;

import org.junit.Test;

public class ImageBundleTest {

	@Test
	public void testInit() {
		ImageBundle bld = ImageBundle.getBundle();
		assertNull(bld);
		ImageBundle.initBundle("superTheme");
		bld = ImageBundle.getBundle();
		assertNotNull(bld);
	}
	
	@Test
	public void testAddImage() {
		ImageBundle.initBundle("mytheme");
		ImageBundle bld = ImageBundle.getBundle();
		URL url = ImageBundleTest.class.getClass().getResource("/images/mytheme/anImage.png");
		bld.addImage(url);
		URL imgUrl = bld.getImageURL("anImage.png");
		assertEquals(url.getFile(), imgUrl.getFile());
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddImageWrong() {
		ImageBundle.initBundle("mytheme1");
		ImageBundle bld = ImageBundle.getBundle();
		URL url = ImageBundleTest.class.getClass().getResource("/images/mytheme/anImage.png");
		bld.addImages(Arrays.asList(url));
		URL imgUrl = bld.getImageURL("anImage.png");
		assertEquals(url.getFile(), imgUrl.getFile());
	}

}

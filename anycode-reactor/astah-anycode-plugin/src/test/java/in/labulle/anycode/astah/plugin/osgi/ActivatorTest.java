package in.labulle.anycode.astah.plugin.osgi;

import static org.mockito.Mockito.when;
import in.labulle.anycode.engine.osgi.Activator;

import java.net.URL;
import java.util.Enumeration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

@RunWith(MockitoJUnitRunner.class)
public class ActivatorTest {
	@Mock
	private BundleContext ctx;
	
	@Mock
	private Bundle bundle; 
	
	@Mock
	private Enumeration<URL> urls;

	@Test
	public void testStart() throws Exception {
		when(ctx.getProperty("isItMyBundle")).thenReturn("yes");
		when(ctx.getBundle()).thenReturn(bundle);
		when(bundle.findEntries("/", "*.gif", true)).thenReturn(urls);	
		new Activator().start(ctx);
		Assert.assertEquals(Activator.getBundleContext(), ctx);
		Assert.assertEquals("yes", ctx.getProperty("isItMyBundle"));
	}

	@Test
	public void testStop() throws Exception {
		new Activator().stop(ctx);
		Assert.assertNull(Activator.getBundleContext());

	}

}

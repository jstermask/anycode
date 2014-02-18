package in.labulle.anycode.astah.plugin.osgi;

import in.labulle.anycode.astah.plugin.osgi.util.BundleUtils;
import in.labulle.anycode.astah.plugin.osgi.util.ImageBundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activator implements BundleActivator {
	private static BundleContext ctx;

	private static final Logger LOG = LoggerFactory.getLogger(Activator.class);


	@Override
	public void start(BundleContext context) throws Exception {
		ctx = context;
		if (LOG.isDebugEnabled()) {
			LOG.debug("Activation done.");
		}
		loadImageLibrary();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		ctx = null;
	}
	
	public static BundleContext getBundleContext() {
		return ctx;
	}
	
	private void loadImageLibrary() {
		String theme = BundleUtils.getMessage("in_labulle_anycode_theme");
		ImageBundle bdl = ImageBundle.initBundle(theme);
		bdl.addImages(BundleUtils.findResources(ctx, "*.gif"));
	}

}

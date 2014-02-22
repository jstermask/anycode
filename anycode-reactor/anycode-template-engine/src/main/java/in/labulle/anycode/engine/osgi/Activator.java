package in.labulle.anycode.engine.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	private static BundleContext ctx;

	

	public void start(BundleContext context) throws Exception {
		ctx = context;
		loadImageLibrary();
	}

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

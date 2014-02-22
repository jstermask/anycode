package in.labulle.anycode.engine.osgi;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import org.osgi.framework.BundleContext;

public class BundleUtils {
	private BundleUtils() {
		
	}
	
	public static List<URL> findResources(BundleContext ctx, String pattern) {
		return findResources(ctx, "/", pattern);
	}
	
	public static URL getResourcePath(BundleContext ctx, String name) {
		return getResourcePath(ctx, "/", name);
	}
	
	public static URL getImage(BundleContext ctx, String imageName) {
		return getResourcePath(ctx, "", imageName);
	}
	
	private static List<URL> findResources(BundleContext ctx, String rootPath, String pattern) {
		List<URL> urls = new ArrayList<URL>();
		if (ctx != null) {
			Enumeration<?> e = ctx.getBundle().findEntries(rootPath, pattern, true);
			while (e.hasMoreElements()) {
				URL u = (URL) e.nextElement();
				urls.add(u);
			}
		}
		return urls;
	}

	private static URL getResourcePath(BundleContext ctx, String rootPath, String name) {
		Enumeration<?> e = ctx.getBundle().findEntries(rootPath, name, true);
		if (e != null) {
			while (e.hasMoreElements()) {
				URL u = (URL) e.nextElement();
				return u;
			}
		}
		return null;
	}
	
	public static String getMessage(String message, Object... objects) {
		return ResourceBundle.getBundle("plugin").getString(message);
	}
}

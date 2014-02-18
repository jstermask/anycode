package in.labulle.anycode.astah.plugin.template.config;

import java.util.HashMap;
/**
 * Configuration is an singleton hashmap that can be cloned explicitely.
 * @author José Carreno
 *
 */
public class Configuration extends HashMap<String,Object> implements Cloneable {
	/**
	 * target directory parameter name.
	 */
	public static final String CONTEXT_PARAM_TARGET_DIR = "targetDir";
	
	/**
	 * template directory parameter name.
	 */
	public static final String CONTEXT_PARAM_TEMPLATE_DIR = "templateDir";
	
	/**
	 * Current IClass instance parameter name.
	 */
	public static final String CONTEXT_PARAM_CLASS_CURRENT = "c";
	
	/**
	 * reference to the reference configuration.
	 */
	private static Configuration CONFIG;

	/**
	 * uid.
	 */
	private static final long serialVersionUID = 6883705042001652156L;

	public static final Configuration getConfiguration() {
		if (CONFIG == null) {
			CONFIG = new Configuration();
		}
		return CONFIG;
	}

	protected Configuration() {
		super();
	}
	
	public Configuration clone() {
		Configuration c = new Configuration();
		c.putAll(this);
		return c;
	}

}

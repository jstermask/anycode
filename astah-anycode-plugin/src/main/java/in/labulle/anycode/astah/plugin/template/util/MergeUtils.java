package in.labulle.anycode.astah.plugin.template.util;

import in.labulle.anycode.astah.plugin.template.freemarker.core.Template;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MergeUtils {
	private static final Logger LOG = LoggerFactory.getLogger(MergeUtils.class);

	private static final String CONTENT_REGEXP = "<customCode id=\"(.*?)\">([\\s\\S]*?)<\\/customCode>";

	private final static Map<String, String> extract(String pattern,
			String content) {
		Map<String, String> map = new HashMap<String, String>();
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(content);
		while (m.find()) {
			String key = m.group(1);
			String value = trimNewLines(m.group(2));
			if (map.containsKey(key)) {
				map.remove(key);
			} else {
				
				map.put(key, value);
				if(LOG.isDebugEnabled()) {
					LOG.debug("Custom code added : " + key + " => " + value);
				}
				
			}
		}
		return map;
	}

	private final static String trimNewLines(String string) {
		String nl = "\n";
		int idx = string.indexOf(nl);
		int lastidx = string.lastIndexOf(nl);
		if (idx != -1 && lastidx != -1) {
			if (idx == 0 && lastidx > 0) {
				idx = 1;
			} else {
				idx = 0;
			}
			if (lastidx > idx) {
				return string.substring(idx, lastidx);
			} else {
				return "";
			}
		}
		return string;
	}

	public final static Map<String, String> findCustomCodes(String content) {
		Map<String, String> map = extract(CONTENT_REGEXP, content);
		return map;
	}

}

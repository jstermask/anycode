package in.labulle.anycode.xmi.parser.jdom.util;

import org.jdom2.Namespace;

public class ParserUtil {
	public static String getNamespacedString(Namespace ns, String value) {
		return ns.getPrefix() + ":" + value;
	}
}

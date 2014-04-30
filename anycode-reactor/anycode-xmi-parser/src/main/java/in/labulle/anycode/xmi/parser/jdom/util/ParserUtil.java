package in.labulle.anycode.xmi.parser.jdom.util;

import in.labulle.anycode.xmi.parser.jdom.uml.IParserContext;

import org.jdom2.Element;
import org.jdom2.Namespace;

public class ParserUtil {
	private static String getNamespacedString(Namespace ns, String value) {
		return ns.getPrefix() + ":" + value;
	}
	
	public static boolean valueEquals(IParserContext ctx, String attribute, String value) {
		return valueEquals(ctx.getCurrentElement(), attribute, value, ctx.getXmiNamespace(), ctx.getUmlNamespace());
	}
	
	public static boolean valueEquals(Element elt, String attribute,  String value, Namespace attributeNamespace, Namespace valueNamespace) {
		return ParserUtil.getNamespacedString(valueNamespace, value).equalsIgnoreCase(elt.getAttributeValue(attribute, attributeNamespace));
	}
}

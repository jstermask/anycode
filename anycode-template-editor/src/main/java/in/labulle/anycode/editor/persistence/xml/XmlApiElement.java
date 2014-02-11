package in.labulle.anycode.editor.persistence.xml;

import in.labulle.anycode.editor.core.ApiElement;
import in.labulle.anycode.editor.core.Param;

import org.jdom2.Element;

public class XmlApiElement {
	public static void copyFromXml(Element element, ApiElement e) {
		e.setName(element.getAttributeValue("name"));
		e.setDescription(element.getChildText("description"));
		e.setCode(element.getChildText("code"));
		for(Element child : element.getChildren("param")) {
			Param p = e.newParam();
			p.setName(child.getChildText("name"));
			p.setDescription(child.getChildText("name"));
		}
	}
	
	public static void copyToXml(Element element, ApiElement e) {

	}
}

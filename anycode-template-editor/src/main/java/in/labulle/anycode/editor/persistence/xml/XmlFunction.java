package in.labulle.anycode.editor.persistence.xml;

import in.labulle.anycode.editor.core.Function;
import in.labulle.anycode.editor.core.Param;

import org.jdom2.Attribute;
import org.jdom2.Element;

public class XmlFunction extends Function implements XmlAble {
	public XmlFunction() {
		super();
	}
	
	protected XmlFunction(Element element) {
		XmlApiElement.copyFromXml(element, this);
	}
	
	@Override
	public Element toXml() {
		Element elt = new Element("function");
		Attribute att = new Attribute("name", getName());
		elt.setAttribute(att);
		return elt;
	}

	@Override
	public String getElementName() {
		return "function";
	}
	
	@Override
	protected Param getParamInstance() {
		return new XmlParam();
	}
}

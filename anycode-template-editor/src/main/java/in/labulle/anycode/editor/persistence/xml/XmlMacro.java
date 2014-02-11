package in.labulle.anycode.editor.persistence.xml;

import org.jdom2.Attribute;
import org.jdom2.Element;

import in.labulle.anycode.editor.core.Macro;
import in.labulle.anycode.editor.core.Param;

public class XmlMacro extends Macro implements XmlAble {

	public XmlMacro() {
		super();
	}
	
	protected XmlMacro(Element element) {
		XmlApiElement.copyFromXml(element, this);
	}
	
	@Override
	public Element toXml() {
		Element elt = new Element("macro");
		Attribute att = new Attribute("name", getName());
		elt.setAttribute(att);
		return elt;
	}

	@Override
	public String getElementName() {
		return "macro";
	}
	
	@Override
	protected Param getParamInstance() {
		return new XmlParam();
	}

}

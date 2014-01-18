package in.labulle.anycode.editor.persistence.xml;

import in.labulle.anycode.editor.core.Param;

import org.jdom2.Element;

public class XmlParam extends Param implements XmlAble {
	public XmlParam() {
		super();
	}
	
	protected XmlParam(Element e) {

	}

	@Override
	public Element toXml() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getElementName() {
		return "param";
	}

}

package in.labulle.anycode.editor.persistence.xml;

import org.jdom2.Element;

public interface XmlAble {
	Element toXml();
	
	String getElementName();
}

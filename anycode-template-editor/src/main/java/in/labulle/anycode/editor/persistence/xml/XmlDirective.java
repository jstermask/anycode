package in.labulle.anycode.editor.persistence.xml;

import org.jdom2.Element;

import in.labulle.anycode.editor.core.ApiElement;
import in.labulle.anycode.editor.core.Directive;

public class XmlDirective extends Directive implements XmlAble {
	public XmlDirective() {
		super();
	}

	public XmlDirective(Element elt) {
		if (elt.getName().equals(getElementName())) {
			for (Element child : elt.getChildren()) {
				addChild(child);
			}
		}
	}

	@Override
	public Element toXml() {
		Element elt = new Element(getElementName());
		for (ApiElement apiElt : getElements()) {
			elt.addContent(((XmlAble) apiElt).toXml());
		}
		return elt;
	}

	@Override
	public String getElementName() {
		return "directive";
	}

	private void addChild(Element element) {
		if ("function".equals(element.getName())) {
			addFunction(element);
		} else {
			addMacro(element);
		}
	}

	private void addFunction(Element element) {
		getElements().add(new XmlFunction(element));
	}

	private void addMacro(Element element) {
		getElements().add(new XmlMacro(element));

	}

}

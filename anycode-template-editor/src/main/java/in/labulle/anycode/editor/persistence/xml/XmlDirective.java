package in.labulle.anycode.editor.persistence.xml;

import org.jdom2.Element;

import in.labulle.anycode.editor.core.ApiElement;
import in.labulle.anycode.editor.core.Directive;
import in.labulle.anycode.editor.core.Function;
import in.labulle.anycode.editor.core.Macro;
import in.labulle.anycode.editor.persistence.InvalidFileException;

public class XmlDirective extends Directive implements XmlAble {
	public XmlDirective() {
		super();
	}

	public XmlDirective(Element elt) {
		if (elt.getName().equals(getElementName())) {
			for (Element child : elt.getChildren()) {
				addChild(child);
			}
		} else {
			throw new InvalidFileException();
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
		} else if("macro".equals(element.getName())){
			addMacro(element);
		} else if("description".equals(element.getName())) {
			setDescription(element.getValue());
		}
	}

	private void addFunction(Element element) {
		getElements().add(new XmlFunction(element));
	}

	private void addMacro(Element element) {
		getElements().add(new XmlMacro(element));

	}
	
	@Override
	protected Function getNewFunctionIntance() {
		return new XmlFunction();
	}
	
	@Override
	protected Macro getNewMacroIntance() {
		return new XmlMacro();
	}

}

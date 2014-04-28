package in.labulle.anycode.xmi.parser.jdom;

import in.labulle.anycode.uml.IElement;

import java.util.HashMap;
import java.util.Map;

import org.jdom2.Element;
import org.jdom2.Namespace;

public class ParserContext implements IParserContext {

	private Namespace umlNamespace;

	private Namespace xmiNamespace;

	private Element currentElement;

	private final Map<String, IElement> parsedElements = new HashMap<String, IElement>();

	private final Map<String, Element> postponedElements = new HashMap<String, Element>();
	
	

	public ParserContext(Namespace umlNamespace, Namespace xmiNamespace, Element currentElement) {
		super();
		this.umlNamespace = umlNamespace;
		this.xmiNamespace = xmiNamespace;
		this.currentElement = currentElement;
	}

	public Namespace getUmlNamespace() {
		return umlNamespace;
	}

	public void setUmlNamespace(Namespace umlNamespace) {
		this.umlNamespace = umlNamespace;
	}

	public Namespace getXmiNamespace() {
		return xmiNamespace;
	}

	public void setXmiNamespace(Namespace xmiNamespace) {
		this.xmiNamespace = xmiNamespace;
	}

	public Element getCurrentElement() {
		return currentElement;
	}

	public void setCurrentElement(Element currentElement) {
		this.currentElement = currentElement;
	}

	public Map<String, IElement> getParsedElements() {
		return parsedElements;
	}

	public Map<String, Element> getPostponedElements() {
		return postponedElements;
	}
	
	public IParserContext clone(Element newElement) {
		ParserContext ctx = new ParserContext(getUmlNamespace(), getXmiNamespace(), newElement);
		return ctx;
	}

}

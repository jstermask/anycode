package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IElement;
import in.labulle.anycode.xmi.parser.IXmiContextParser;

import java.util.HashMap;
import java.util.Map;

import org.jdom2.Element;
import org.jdom2.Namespace;

public class ParserContext implements IParserContext {

	private Namespace umlNamespace;

	private Namespace xmiNamespace;

	private Element currentElement;

	private final Map<String, IElement> parsedElements;

	private final Map<String, IXmiContextParser<?>> postponedElements;

	public ParserContext(Namespace umlNamespace, Namespace xmiNamespace, Element currentElement) {
		super();
		this.umlNamespace = umlNamespace;
		this.xmiNamespace = xmiNamespace;
		this.currentElement = currentElement;
		this.parsedElements = new HashMap<String, IElement>();
		this.postponedElements = new HashMap<String, IXmiContextParser<?>>();
	}

	private ParserContext(Namespace umlNamespace, Namespace xmiNamespace, Element currentElement, Map<String, IElement> parsedElts,
			Map<String, IXmiContextParser<?>> postElts) {
		super();
		this.umlNamespace = umlNamespace;
		this.xmiNamespace = xmiNamespace;
		this.currentElement = currentElement;
		this.parsedElements = parsedElts;
		this.postponedElements = postElts;
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

	public Map<String, IXmiContextParser<?>> getPostponedElements() {
		return postponedElements;
	}

	public IParserContext clone(Element newElement) {
		ParserContext ctx = new ParserContext(getUmlNamespace(), getXmiNamespace(), newElement, this.parsedElements, this.postponedElements);
		return ctx;
	}

}

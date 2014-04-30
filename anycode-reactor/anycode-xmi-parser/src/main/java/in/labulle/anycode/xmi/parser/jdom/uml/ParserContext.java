package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Element;
import org.jdom2.Namespace;

public class ParserContext implements IParserContext {

	
	private Namespace umlNamespace;

	private Namespace xmiNamespace;

	private Element currentElement;

	private final Map<String, IElement> parsedElements;

	private final List<PostPonedTask> postPonedTasks;

	public ParserContext(Namespace umlNamespace, Namespace xmiNamespace, Element currentElement) {
		super();
		this.umlNamespace = umlNamespace;
		this.xmiNamespace = xmiNamespace;
		this.currentElement = currentElement;
		this.parsedElements = new HashMap<String, IElement>();
		this.postPonedTasks = new ArrayList<PostPonedTask>();
	}

	private ParserContext(Namespace umlNamespace, Namespace xmiNamespace, Element currentElement, Map<String, IElement> parsedElts,
			List<PostPonedTask> tasks) {
		super();
		this.umlNamespace = umlNamespace;
		this.xmiNamespace = xmiNamespace;
		this.currentElement = currentElement;
		this.parsedElements = parsedElts;
		this.postPonedTasks = tasks;
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

	public List<PostPonedTask> getPostPonedTasks() {
		return postPonedTasks;
	}

	public IParserContext clone(Element newElement) {
		ParserContext ctx = new ParserContext(getUmlNamespace(), getXmiNamespace(), newElement, this.parsedElements, this.postPonedTasks);
		return ctx;
	}

}

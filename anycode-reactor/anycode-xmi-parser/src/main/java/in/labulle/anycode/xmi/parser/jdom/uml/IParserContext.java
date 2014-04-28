package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IElement;

import java.util.Map;

import org.jdom2.Element;
import org.jdom2.Namespace;

public interface IParserContext {
	Namespace getUmlNamespace();
	Namespace getXmiNamespace();
	Element getCurrentElement();
	Map<String, Element> getPostponedElements();
	Map<String, IElement> getParsedElements();
	IParserContext clone(Element elt);
}

package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IElement;

import java.util.List;
import java.util.Map;

import org.jdom2.Element;
import org.jdom2.Namespace;

public interface IParserContext {
	Namespace getUmlNamespace();
	Namespace getXmiNamespace();
	Element getCurrentElement();
	List<PostPonedTask> getPostPonedTasks();
	Map<String, IElement> getParsedElements();
	IParserContext clone(Element elt);
}

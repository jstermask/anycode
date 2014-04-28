package in.labulle.anycode.xmi.parser.jdom;

import in.labulle.anycode.xmi.parser.XmiInvalidParserException;
import in.labulle.anycode.xmi.parser.XmiParser;
import in.labulle.anycode.xmi.parser.jdom.util.ParserUtil;

import org.jdom2.Element;
import org.jdom2.Namespace;

public abstract class IElementParser<T> implements XmiParser<T> {
	private Element element;

	private Namespace umlNamespace;

	private Namespace xmiNamespace;

	public IElementParser(final Element elt, final Namespace umlNamespace, final Namespace xmiNamespace) {
		this.element = elt;
		this.umlNamespace = umlNamespace;
		this.xmiNamespace = xmiNamespace;
		if (!matches()) {
			throw new XmiInvalidParserException("Parser " + getClass().getName() + " is not valid for this element");
		}
	}

	protected abstract boolean matches();

	protected Element getElement() {
		return element;
	}

	protected Namespace getUmlNamespace() {
		return umlNamespace;
	}

	protected Namespace getXmiNamespace() {
		return xmiNamespace;
	}

	protected boolean isElementType(String typeName) {
		return ParserUtil.getNamespacedString(getUmlNamespace(), typeName).equalsIgnoreCase(getElement().getAttributeValue("type", getXmiNamespace()));
	}
	
	public IElementParser newInstance(Element e) {
		IPackageParser p = new IPackageParser(e, getUmlNamespace(), getXmiNamespace());
		if(p.matches()) {
			return p;
		} else {
			return null;
		}
	}

}
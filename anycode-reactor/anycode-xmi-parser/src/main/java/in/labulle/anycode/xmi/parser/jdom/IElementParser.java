package in.labulle.anycode.xmi.parser.jdom;

import in.labulle.anycode.xmi.parser.XmiInvalidParserException;
import in.labulle.anycode.xmi.parser.XmiParser;
import in.labulle.anycode.xmi.parser.jdom.util.ParserUtil;

public abstract class IElementParser<T> implements XmiParser<T> {
	private IParserContext parserContext;

	public IElementParser(final IParserContext ctx) {
		this.parserContext = ctx;
		if (!matches()) {
			throw new XmiInvalidParserException("Parser " + getClass().getSimpleName() + " is not valid for the given element (" + getParserContext().getCurrentElement().getName() + ")");
		}
	}

	protected abstract boolean matches();

	protected IParserContext getParserContext() {
		return parserContext;
	}

	protected boolean isElementType(String typeName) {
		return ParserUtil.getNamespacedString(getParserContext().getUmlNamespace(), typeName).equalsIgnoreCase(getParserContext().getCurrentElement().getAttributeValue("type", getParserContext().getXmiNamespace()));
	}

}
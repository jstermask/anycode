package in.labulle.anycode.xmi.parser;

import in.labulle.anycode.xmi.parser.jdom.uml.IParserContext;


public interface IXmiContextParser<T> {
	T parse(IParserContext ctx);
	boolean matches(IParserContext ctx);
}

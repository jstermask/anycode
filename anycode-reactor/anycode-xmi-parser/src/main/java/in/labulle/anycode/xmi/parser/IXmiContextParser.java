package in.labulle.anycode.xmi.parser;

import in.labulle.anycode.xmi.parser.jdom.uml.IParserContext;


public interface IXmiContextParser<T> {
	T parse();
	boolean matches(IParserContext ctx);
}

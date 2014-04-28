package in.labulle.anycode.xmi.parser;

import in.labulle.anycode.xmi.parser.jdom.uml.IParserContext;

public interface IXmiContextParserFactory {
	IXmiContextParser<?> newInstance(IParserContext ctx);
}

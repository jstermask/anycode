package in.labulle.anycode.xmi.parser.jdom.uml;

import java.util.Arrays;
import java.util.List;

import in.labulle.anycode.xmi.parser.IXmiContextParser;
import in.labulle.anycode.xmi.parser.IXmiContextParserFactory;

public class XmiContextParserFactory implements IXmiContextParserFactory {
	@SuppressWarnings("unchecked")
	public final List<IXmiContextParser<?>> parsers = Arrays.asList((IXmiContextParser<?>)(new IModelParser()), new IPackageParser(), new IClassParser());
	
	
	public XmiContextParserFactory() {
		
	}

	public IXmiContextParser<?> newInstance(IParserContext ctx) {
		for(IXmiContextParser<?> parser : parsers) {
			if(parser.matches(ctx)) {
				return parser;
			}
		}
		return null;
	}

}

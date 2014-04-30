package in.labulle.anycode.xmi.parser.jdom.uml;

import java.util.Arrays;
import java.util.List;

import in.labulle.anycode.xmi.parser.IXmiContextParser;
import in.labulle.anycode.xmi.parser.IXmiContextParserFactory;
import in.labulle.anycode.xmi.parser.exception.XmiInvalidParserException;

public class XmiContextParserFactory implements IXmiContextParserFactory {
	private static IXmiContextParserFactory instance = null;

	@SuppressWarnings("unchecked")
	public final List<Class<?>> parsersClasses = Arrays.asList((Class<?>) IModelParser.class, IPackageParser.class, IClassParser.class, IInterfaceParser.class, IAttributeParser.class, IAttributePrimitiveDatatypeParser.class, IRelationAttributeParser.class);

	public XmiContextParserFactory() {

	}

	public IXmiContextParser<?> newInstance(IParserContext ctx) {
		for (Class<?> c : parsersClasses) {
			try {
				IXmiContextParser<?> parser = (IXmiContextParser<?>) c.getConstructor(IParserContext.class).newInstance(ctx);
				if (parser.matches(ctx)) {
					return parser;
				}
			} catch (Exception e) {
				throw new XmiInvalidParserException("Parser could not be instanciated", e);
			}
		}
		return null;
	}

	public static IXmiContextParserFactory get() {
		if (instance == null) {
			instance = new XmiContextParserFactory();
		}
		return instance;
	}

}

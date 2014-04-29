package in.labulle.anycode.xmi.parser.jdom.uml;

import org.jdom2.Element;

import in.labulle.anycode.xmi.parser.IXmiContextParser;

public abstract class IElementParser<T> implements IXmiContextParser<T> {
	private IParserContext parserContext;

	public IElementParser(IParserContext ctx) {
		this.parserContext = ctx;
	}

	protected IParserContext getParserContext() {
		return parserContext;
	}

	protected abstract T newInstance();

	protected abstract void init(T obj);

	public T parse() {
		T obj = newInstance();
		init(obj);
		for (Element sElt : getParserContext().getCurrentElement().getChildren()) {
			performParse(obj, sElt);
		}
		return obj;
	}

	protected void performParse(T obj, Element elt) {
		IParserContext sCtx = getParserContext().clone(elt);
		IElementParser<?> parser = (IElementParser<?>) XmiContextParserFactory.get().newInstance(sCtx);
		if (parser != null) {
			Object o = parser.parse();
			attachChild(obj, o);
		}
	}
	
	protected abstract void attachChild(T currentObj, Object child);

}
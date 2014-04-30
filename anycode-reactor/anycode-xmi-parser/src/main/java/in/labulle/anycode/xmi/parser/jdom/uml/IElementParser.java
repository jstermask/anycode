package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IElement;
import in.labulle.anycode.xmi.parser.IXmiContextParser;

import org.jdom2.Element;

public abstract class IElementParser<T> implements IXmiContextParser<T> {
	private IParserContext parserContext;

	public IElementParser(IParserContext ctx) {
		this.parserContext = ctx;
	}

	protected IParserContext getParserContext() {
		return parserContext;
	}

	protected abstract T newInstance();

	protected abstract boolean init(T obj);

	public T parse() {
		T obj = newInstance();
		boolean initDone = init(obj);
		if (initDone) {
			storeObj(obj);
			for (Element sElt : getParserContext().getCurrentElement().getChildren()) {
				performParse(obj, sElt);
			}
		} else {
			postPoneObj(obj);
		}
		completeParsing();
		return obj;
	}
	
	protected void completeParsing() {
		
	}

	protected void performParse(T obj, Element elt) {
		IParserContext sCtx = getParserContext().clone(elt);
		IElementParser<?> parser = (IElementParser<?>) XmiContextParserFactory.get().newInstance(sCtx);
		if (parser != null) {
			Object o = parser.parse();
			attachChild(obj, o);
		}
	}
	
	protected void performPostponedParse(Element elt) {
		
	}

	protected abstract void attachChild(T currentObj, Object child);

	protected void storeObj(T obj) {
		String xmiId = getParserContext().getCurrentElement().getAttributeValue("id", getParserContext().getXmiNamespace());
		if (xmiId != null) {
			getParserContext().getParsedElements().put(xmiId, (IElement) obj);
		}
		getParserContext().getPostponedElements().remove(xmiId);
	}

	protected void postPoneObj(T obj) {
		String xmiId = getParserContext().getCurrentElement().getAttributeValue("id", getParserContext().getXmiNamespace());
		if (xmiId != null) {
			getParserContext().getPostponedElements().put(xmiId, this);
		}
	}

}
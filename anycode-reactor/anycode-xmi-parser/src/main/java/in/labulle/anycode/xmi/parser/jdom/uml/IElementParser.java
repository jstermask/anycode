package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IElement;
import in.labulle.anycode.xmi.parser.IXmiContextParser;

import org.jdom2.Element;

public abstract class IElementParser<T> implements IXmiContextParser<T> {
	private IParserContext parserContext;

	private IElementParser<?> parentParser;

	private T umlElement;

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
		} else {
			postPone();
		}
		if (initDone) {
			for (Element sElt : getParserContext().getCurrentElement().getChildren()) {
				performChildParse(obj, sElt);
			}
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	protected void completeParsing() {
		T obj = parse();
		Object umlElement = getParentParser().getUmlElement();
		getParentParser().attachChild(umlElement, obj);
	}

	protected void performChildParse(T obj, Element elt) {
		IParserContext sCtx = getParserContext().clone(elt);
		IElementParser<?> parser = (IElementParser<?>) XmiContextParserFactory.get().newInstance(sCtx);
		if (parser != null) {
			parser.setParentParser(this);
			Object o = parser.parse();
			attachChild(obj, o);
		}

	}

	protected abstract void attachChild(T currentObj, Object child);

	protected void storeObj(T obj) {
		String xmiId = getParserContext().getElementId();
		if (xmiId != null) {
			getParserContext().getParsedElements().put(xmiId, (IElement) obj);
		}
		getParserContext().getPostPonedTasks().remove(new PostPonedTask(xmiId));
	}

	protected void postPone() {
		String xmiId = getParserContext().getElementId();
		if (xmiId != null) {
			getParserContext().getPostPonedTasks().add(new PostPonedTask(xmiId, this));
		}
	}

	@SuppressWarnings("rawtypes")
	protected IElementParser getParentParser() {
		return parentParser;
	}

	protected void setParentParser(@SuppressWarnings("rawtypes") IElementParser parentParser) {
		this.parentParser = parentParser;
	}

	private T getUmlElement() {
		return umlElement;
	}

}
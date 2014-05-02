package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.xmi.parser.jdom.util.ParserUtil;

public class IClientDependencyParser extends IElementParser<IClassifier> {

	public IClientDependencyParser(IParserContext ctx) {
		super(ctx);
	}

	public boolean matches(IParserContext ctx) {
		return ParserUtil.valueEquals(ctx, "type", "Usage") || ParserUtil.valueEquals(ctx, "type", "Dependency");
	}

	@Override
	protected IClassifier newInstance() {
		return (IClassifier) getParserContext().getParsedElements().get(getParserContext().getCurrentElement().getAttributeValue("client"));
	}

	@Override
	protected boolean init(IClassifier obj) {
		if (obj != null) {
			IClassifier supplier = (IClassifier) getParserContext().getParsedElements().get(
					getParserContext().getCurrentElement().getAttributeValue("supplier"));
			if (supplier != null) {
				if (!obj.getClientDependencies().contains(supplier)) {
					obj.getClientDependencies().add(supplier);
				}
				return true;
			}
		}
		return false;
	}

	@Override
	protected void attachChild(IClassifier currentObj, Object child) {

	}

}

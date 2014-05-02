package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IParameter;
import in.labulle.anycode.uml.impl.AParameter;

public class IParameterParser extends IElementParser<IParameter> {

	private final static String TAG_NAME = "ownedParameter";

	public IParameterParser(IParserContext ctx) {
		super(ctx);
	}

	public boolean matches(IParserContext ctx) {
		return TAG_NAME.equalsIgnoreCase(getParserContext().getCurrentElement().getName());
	}

	@Override
	protected IParameter newInstance() {
		return new AParameter();
	}

	@Override
	protected boolean init(IParameter obj) {
		if (obj instanceof AParameter) {
			AParameter p = (AParameter) obj;
			p.setName(getParserContext().getElementName());
			String paramDirection = getParserContext().getCurrentElement().getAttributeValue("direction");
			if ("return".equalsIgnoreCase(paramDirection)) {
				p.setReturnType(true);
			}
			String typeXmiId = getParserContext().getElementDataType();
			if (typeXmiId != null) {
				IClassifier type = (IClassifier) getParserContext().getParsedElements().get(typeXmiId);
				if (type == null) {
					return false;
				} else {
					p.setDataType(type);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	protected void attachChild(IParameter currentObj, Object child) {

	}

}

package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.IInterface;
import in.labulle.anycode.uml.impl.AClass;

public class IClassRealizationParser extends IElementParser<IClass> {

	private static final String TAG_NAME = "interfaceRealization";

	public IClassRealizationParser(IParserContext ctx) {
		super(ctx);
	}

	public boolean matches(IParserContext ctx) {
		return TAG_NAME.equalsIgnoreCase(ctx.getCurrentElement().getName());
	}

	@Override
	protected IClass newInstance() {
		IClass cl = (IClass) getParserContext().getParsedElements().get(getParserContext().getCurrentElement().getAttributeValue("client"));
		return cl;
	}

	@Override
	protected boolean init(IClass obj) {
		if (obj != null) {
			AClass c = (AClass) obj;
			IInterface i = (IInterface) getParserContext().getParsedElements().get(getParserContext().getCurrentElement().getAttributeValue("supplier"));
			if (i != null) {
				c.getRealizations().add(i);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	protected void attachChild(IClass currentObj, Object child) {
		// TODO Auto-generated method stub

	}

}

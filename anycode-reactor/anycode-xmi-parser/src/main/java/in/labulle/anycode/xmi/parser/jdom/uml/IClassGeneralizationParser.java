package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.impl.AClass;
import in.labulle.anycode.xmi.parser.jdom.util.ParserUtil;

public class IClassGeneralizationParser extends IElementParser<IClass> {

	public IClassGeneralizationParser(IParserContext ctx) {
		super(ctx);
	}

	public boolean matches(IParserContext ctx) {
		return ParserUtil.valueEquals(ctx, "type", "Generalization");
	}

	@Override
	protected IClass newInstance() {
		IClass cl = (IClass) getParserContext().getParsedElements().get(getParentParser().getParserContext().getElementId());
		return cl;
	}

	@Override
	protected boolean init(IClass obj) {
		if (obj != null) {
			AClass c = (AClass) obj;
			IClass parent = (IClass) getParserContext().getParsedElements().get(getParserContext().getCurrentElement().getAttributeValue("general"));
			if (parent != null) {
				c.getGeneralizations().add(parent);
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

package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.Visibility;
import in.labulle.anycode.uml.impl.AClass;
import in.labulle.anycode.xmi.parser.jdom.util.ParserUtil;

public class IAttributePrimitiveDatatypeParser extends IElementParser<IClass> {

	public IAttributePrimitiveDatatypeParser(IParserContext ctx) {
		super(ctx);
	}

	public boolean matches(IParserContext ctx) {
		return ParserUtil.valueEquals(ctx, "type", "PrimitiveType");
	}

	@Override
	protected IClass newInstance() {
		return new AClass();
	}

	@Override
	protected boolean init(IClass obj) {
		if (obj instanceof AClass) {
			AClass c = (AClass) obj;
			c.setPrimitive(true);
			String href = getParserContext().getCurrentElement().getAttributeValue("href");
			c.setName(href.substring(href.lastIndexOf("#") + 1));
			c.setVisibility(Visibility.PUBLIC);
			return true;
		}
		return false;
	}

	@Override
	protected void attachChild(IClass currentObj, Object child) {

	}

}

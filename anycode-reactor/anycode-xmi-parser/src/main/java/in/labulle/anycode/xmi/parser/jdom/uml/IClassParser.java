package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.impl.AClass;
import in.labulle.anycode.xmi.parser.jdom.util.ParserUtil;

public class IClassParser extends IElementParser<IClass> {



	public IClassParser(IParserContext ctx) {
		super(ctx);
	}

	public boolean matches(IParserContext ctx) {
		return ParserUtil.valueEquals(ctx, "type", "Class");
	}

	@Override
	protected IClass newInstance() {
		return new AClass();
	}

	@Override
	protected boolean init(IClass obj) {
		if(obj instanceof AClass) {
			AClass c = (AClass)obj;
			c.setName(getParserContext().getCurrentElement().getAttributeValue("name"));
			return true;
		}
		return false;
	}

	@Override
	protected void attachChild(IClass currentObj, Object child) {
		
		
	}

}

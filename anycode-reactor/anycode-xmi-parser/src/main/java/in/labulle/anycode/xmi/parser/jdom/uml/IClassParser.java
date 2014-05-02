package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.IOperation;
import in.labulle.anycode.uml.impl.AAttribute;
import in.labulle.anycode.uml.impl.AClass;
import in.labulle.anycode.uml.impl.AOperation;
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
			c.setName(getParserContext().getElementName());
			c.setAbstract(getParserContext().isElementAbstract());
			return true;
		}
		return false;
	}

	@Override
	protected void attachChild(IClass currentObj, Object child) {
		if(child instanceof IAttribute) {
			((AClass)currentObj).addAttribute((IAttribute)child);
			((AAttribute)child).setOwner(currentObj);
		}
		if(child instanceof IOperation) {
			((AClass)currentObj).addOperation((IOperation)child);
			((AOperation)child).setOwner(currentObj);
		}
	}

}

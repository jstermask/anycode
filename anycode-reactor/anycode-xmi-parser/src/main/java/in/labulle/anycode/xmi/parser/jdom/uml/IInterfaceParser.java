package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IInterface;
import in.labulle.anycode.uml.IOperation;
import in.labulle.anycode.uml.impl.AAttribute;
import in.labulle.anycode.uml.impl.AInterface;
import in.labulle.anycode.uml.impl.AOperation;
import in.labulle.anycode.xmi.parser.jdom.util.ParserUtil;

public class IInterfaceParser extends IElementParser<IInterface> {


	public IInterfaceParser(IParserContext ctx) {
		super(ctx);
	}

	public boolean matches(IParserContext ctx) {
		return ParserUtil.valueEquals(ctx, "type", "Interface");
	}

	@Override
	protected IInterface newInstance() {
		return new AInterface();
	}

	@Override
	protected boolean init(IInterface obj) {
		if(obj instanceof AInterface) {
			AInterface i = (AInterface)obj;
			i.setName(getParserContext().getElementName());
			return true;
		}
		return false;
	}

	@Override
	protected void attachChild(IInterface currentObj, Object child) {
		if(child instanceof IAttribute) {
			((AInterface)currentObj).addAttribute((IAttribute)child);
			((AAttribute)child).setOwner(currentObj);
		}
		if(child instanceof IOperation) {
			((AInterface)currentObj).addOperation((IOperation)child);
			((AOperation)child).setOwner(currentObj);
		}
		
	}

}

package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IInterface;
import in.labulle.anycode.uml.impl.AInterface;
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
		
		
	}

}

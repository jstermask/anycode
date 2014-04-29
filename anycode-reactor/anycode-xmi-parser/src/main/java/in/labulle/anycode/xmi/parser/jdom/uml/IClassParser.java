package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.impl.AClass;

public class IClassParser extends IElementParser<IClass> {



	public IClassParser(IParserContext ctx) {
		super(ctx);
	}

	public boolean matches(IParserContext ctx) {
		return false;
	}

	@Override
	protected IClass newInstance() {
		return new AClass();
	}

	@Override
	protected void init(IClass obj) {
		if(obj instanceof AClass) {
			AClass c = (AClass)obj;
		}
	}

	@Override
	protected void attachChild(IClass currentObj, Object child) {
		
		
	}

}

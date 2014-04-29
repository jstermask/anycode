package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IPackage;
import in.labulle.anycode.uml.impl.APackage;
import in.labulle.anycode.xmi.parser.jdom.util.ParserUtil;

public class IPackageParser extends IElementParser<IPackage> {

	public IPackageParser(IParserContext ctx) {
		super(ctx);
	}


	public boolean matches(IParserContext ctx) {
		return ParserUtil.valueEquals(ctx, "type", "Package");
	}

	@Override
	protected IPackage newInstance() {
		return new APackage();
	}

	@Override
	protected void init(IPackage obj) {
		if(obj instanceof APackage) {
			APackage p = (APackage)obj;
		}
	}


	@Override
	protected void attachChild(IPackage currentObj, Object child) {
		if (child instanceof IPackage) {
			currentObj.addSubPackage(((IPackage) child));
		}
		if (child instanceof IClassifier) {
			currentObj.addClassifier((IClassifier) child);
		}
		
	}

}

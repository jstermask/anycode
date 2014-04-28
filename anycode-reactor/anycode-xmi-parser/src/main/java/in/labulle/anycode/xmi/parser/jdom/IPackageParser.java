package in.labulle.anycode.xmi.parser.jdom;

import in.labulle.anycode.uml.IPackage;
import in.labulle.anycode.uml.impl.APackage;

import org.jdom2.Element;

public class IPackageParser extends IElementParser<IPackage> {

	public IPackageParser(IParserContext parserContext) {
		super(parserContext);
	}

	public IPackage parse() {
		APackage pack = new APackage();
		pack.setName(getParserContext().getCurrentElement().getAttributeValue("name"));
		for(Element child : getParserContext().getCurrentElement().getChildren()) {
			IPackageParser p = new IPackageParser(getParserContext().clone(child));
		}
		return pack;
	}

	@Override
	protected boolean matches() {
		return isElementType("Package");
	}

}

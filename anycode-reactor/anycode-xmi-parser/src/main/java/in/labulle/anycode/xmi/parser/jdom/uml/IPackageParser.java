package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IPackage;
import in.labulle.anycode.uml.impl.APackage;
import in.labulle.anycode.xmi.parser.jdom.util.ParserUtil;

import org.jdom2.Element;

public class IPackageParser extends IElementParser<IPackage> {

	public IPackage parse(IParserContext ctx) {
		APackage pack = new APackage();
		pack.setName(ctx.getCurrentElement().getAttributeValue("name"));
		for (Element child : ctx.getCurrentElement().getChildren()) {
			IPackageParser p = new IPackageParser();
		}
		return pack;
	}

	public boolean matches(IParserContext ctx) {
		return ParserUtil.valueEquals(ctx, "type", "Package");
	}

}

package in.labulle.anycode.xmi.parser.jdom;

import in.labulle.anycode.uml.IPackage;
import in.labulle.anycode.uml.impl.APackage;

import org.jdom2.Element;
import org.jdom2.Namespace;

public class IPackageParser extends IElementParser<IPackage> {

	public IPackageParser(Element elt, Namespace umlNamespace, Namespace xmiNamespace) {
		super(elt, umlNamespace, xmiNamespace);
	}

	public IPackage parse() {
		APackage pack = new APackage();
		pack.setName(getElement().getAttributeValue("name"));
		for(Element child : getElement().getChildren()) {
			IPackageParser p = new IPackageParser(child, getUmlNamespace(), getXmiNamespace());
			
		}
		return pack;
	}

	@Override
	protected boolean matches() {
		return isElementType("Package");
	}

}

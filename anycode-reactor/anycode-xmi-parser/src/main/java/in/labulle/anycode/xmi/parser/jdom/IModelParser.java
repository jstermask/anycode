package in.labulle.anycode.xmi.parser.jdom;

import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.uml.impl.AModel;
import in.labulle.anycode.xmi.parser.XmiInvalidParserException;

import org.jdom2.Element;
import org.jdom2.Namespace;

public class IModelParser extends IElementParser<IModel> {
	private static final String TAG_MODEL = "Model";



	public IModelParser(Element elt, Namespace umlNamespace, Namespace xmiNamespace) {
		super(elt, umlNamespace, xmiNamespace);
	}

	public IModel parse() {
		AModel model = new AModel();
		for(Element elt : getElement().getChildren()) {
			try {
				IPackageParser p = new IPackageParser(elt, getUmlNamespace(), getXmiNamespace());
				model.getPackages().add(p.parse());
			} catch(XmiInvalidParserException e) {
				
			}
		}
		return model;
	}
	
	private String getModelTagName() {
		return TAG_MODEL;
	}

	@Override
	protected boolean matches() {
		return getModelTagName().equalsIgnoreCase(getElement().getName());
	}
}

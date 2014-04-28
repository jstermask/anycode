package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.uml.impl.AModel;
import in.labulle.anycode.xmi.parser.exception.XmiInvalidParserException;

import org.jdom2.Element;

public class IModelParser extends IElementParser<IModel> {
	private static final String TAG_MODEL = "Model";



	public IModel parse(IParserContext ctx) {
		AModel model = new AModel();
		for(Element elt : ctx.getCurrentElement().getChildren()) {
			try {
				IPackageParser p = new IPackageParser();
				model.getPackages().add(p.parse(ctx.clone(elt)));
			} catch(XmiInvalidParserException e) {
				
			}
		}
		return model;
	}
	
	private String getModelTagName() {
		return TAG_MODEL;
	}


	public boolean matches(IParserContext ctx) {
		return getModelTagName().equalsIgnoreCase(ctx.getCurrentElement().getName());
	}
}

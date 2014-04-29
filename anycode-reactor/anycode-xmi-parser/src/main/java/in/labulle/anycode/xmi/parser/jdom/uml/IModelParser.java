package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.uml.IPackage;
import in.labulle.anycode.uml.impl.AModel;

public class IModelParser extends IElementParser<IModel> {

	private static final String TAG_MODEL = "Model";

	public IModelParser(IParserContext ctx) {
		super(ctx);
	}

	private String getModelTagName() {
		return TAG_MODEL;
	}

	public boolean matches(IParserContext ctx) {
		return getModelTagName().equalsIgnoreCase(ctx.getCurrentElement().getName());
	}

	@Override
	protected IModel newInstance() {
		return new AModel();
	}

	@Override
	protected void init(IModel obj) {
		if (obj instanceof AModel) {
			AModel m = (AModel) obj;
		}
	}

	@Override
	protected void attachChild(IModel currentObj, Object child) {
		if (child instanceof IPackage) {
			currentObj.getPackages().add(((IPackage) child));
		}
		if (child instanceof IClassifier) {
			currentObj.getClassifiers().add((IClassifier) child);
		}

	}
}

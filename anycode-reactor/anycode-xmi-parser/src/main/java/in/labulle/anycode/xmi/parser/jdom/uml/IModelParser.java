package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.uml.IPackage;
import in.labulle.anycode.uml.impl.AModel;
import in.labulle.anycode.xmi.parser.exception.XmiParserException;

import java.util.ArrayList;
import java.util.List;

public class IModelParser extends IElementParser<IModel> {

	public static final String TAG_MODEL = "Model";

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
	protected boolean init(IModel obj) {
		if (obj instanceof AModel) {
			return true;
		}
		return false;
	}

	@Override
	protected void attachChild(IModel currentObj, Object child) {
		if (child instanceof IPackage) {
			if (!currentObj.getPackages().contains(child)) {
				currentObj.getPackages().add(((IPackage) child));
			}
		}
		if (child instanceof IClassifier && !((IClassifier)child).isPrimitive()) {
			if (!currentObj.getClassifiers().contains(child)) {
				currentObj.getClassifiers().add((IClassifier) child);
			}
		}
	}

	@Override
	public IModel parse() {
		IModel model = super.parse();
		completeParsing();
		return model;
	}

	@Override
	protected void completeParsing() {
		int maxIt = 500;
		int curIt = 0;
		while (!getParserContext().getPostPonedTasks().isEmpty() || curIt < maxIt) {
			curIt++;
			List<PostPonedTask> tasks = new ArrayList<PostPonedTask>(getParserContext().getPostPonedTasks());
			for (PostPonedTask t : tasks) {
				t.getParser().parse();
			}
		}
		if (!getParserContext().getPostPonedTasks().isEmpty()) {
			throw new XmiParserException("Those tags could never be parsed : " + getParserContext().getPostPonedTasks());
		}
	}

	protected void storeObj(IModel obj) {

	}
}

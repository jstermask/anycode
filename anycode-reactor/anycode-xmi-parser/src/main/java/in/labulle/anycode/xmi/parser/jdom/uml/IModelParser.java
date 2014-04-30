package in.labulle.anycode.xmi.parser.jdom.uml;

import java.util.Collection;
import java.util.HashSet;

import org.ietf.jgss.Oid;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IElement;
import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.uml.IPackage;
import in.labulle.anycode.uml.impl.AModel;
import in.labulle.anycode.xmi.parser.IXmiContextParser;
import in.labulle.anycode.xmi.parser.exception.XmiParserException;

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
	protected boolean init(IModel obj) {
		if (obj instanceof AModel) {
			AModel m = (AModel) obj;
			return true;
		}
		return false;
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
	
	@Override
	protected void completeParsing() {
		int maxIt = 500;
		int curIt = 0;
		while(!getParserContext().getPostPonedTasks().isEmpty() || curIt < maxIt) {
			curIt++;
			for(PostPonedTask t : getParserContext().getPostPonedTasks()) {
				t.getParser().parse();
			}
		}
		if(!getParserContext().getPostPonedTasks().isEmpty()) {
			throw new XmiParserException("Those tags could never be parsed : " + getParserContext().getPostPonedTasks());
		}
	}
	
	protected void storeObj(IModel obj) {
		
	}
}

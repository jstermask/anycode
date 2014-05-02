package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IOperation;
import in.labulle.anycode.uml.IParameter;
import in.labulle.anycode.uml.Visibility;
import in.labulle.anycode.uml.impl.AOperation;
import in.labulle.anycode.uml.impl.AParameter;
import in.labulle.anycode.xmi.parser.jdom.util.ParserUtil;

public class IOperationParser extends IElementParser<IOperation> {

	public IOperationParser(IParserContext ctx) {
		super(ctx);
	}

	public boolean matches(IParserContext ctx) {
		return ParserUtil.valueEquals(ctx, "type", "Operation");
	}

	@Override
	protected IOperation newInstance() {
		return new AOperation();
	}

	@Override
	protected boolean init(IOperation obj) {
		if (obj instanceof AOperation) {
			AOperation o = (AOperation) obj;
			o.setName(getParserContext().getElementName());
			o.setVisibility(Visibility.valueOf(getParserContext().getElementVisibility().toUpperCase()));
			o.setAbstract(getParserContext().isElementAbstract());
			o.setReturnType(getDatatype());
			return true;
		}
		return false;
	}

	@Override
	protected void attachChild(IOperation currentObj, Object child) {
		AOperation op = (AOperation) currentObj;
		if (child instanceof IParameter) {
			AParameter p = (AParameter) child;
			if (p.isReturnType()) {
				op.setReturnType(p.getDataType());
			} else {
				op.addParameter(p);
				p.setOwner(currentObj);
			}
		}
	}

	protected IClassifier getDatatype() {
		return (IClassifier) getParserContext().getParsedElements().get(getParserContext().getElementDataType());
	}
}

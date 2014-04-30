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
			o.setReturnType(getDatatype());
			return true;
		}
		return false;
	}

	@Override
	protected void attachChild(IOperation currentObj, Object child) {
		if (child instanceof IParameter) {
			((AOperation) currentObj).addParameter((IParameter) child);
			((AParameter) child).setOwner(currentObj);
		}
	}

	protected IClassifier getDatatype() {
		return (IClassifier) getParserContext().getParsedElements().get(getParserContext().getElementDataType());
	}
}

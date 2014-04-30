package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.Cardinality;
import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.Visibility;
import in.labulle.anycode.uml.impl.AAttribute;
import in.labulle.anycode.xmi.parser.jdom.util.ParserUtil;

public class IAttributeParser extends IElementParser<IAttribute> {



	public IAttributeParser(IParserContext ctx) {
		super(ctx);
	}

	public boolean matches(IParserContext ctx) {
		return ParserUtil.valueEquals(ctx, "type", "Property") && getParserContext().getCurrentElement().getAttribute("association") == null;
	}

	@Override
	protected  IAttribute newInstance() {
		return new AAttribute();
	}

	@Override
	protected boolean init(IAttribute obj) {
		if(obj instanceof AAttribute) {
			AAttribute a = ( AAttribute)obj;
			a.setName(getParserContext().getElementName());
			a.setVisibility(Visibility.valueOf(getParserContext().getElementVisibility().toUpperCase()));
			a.setCardinality(Cardinality.ONE_TO_ONE);
			return true;
		}
		return false;
	}
	

	@Override
	protected void attachChild(IAttribute currentObj, Object child) {
		if(child instanceof IClassifier) {
			((AAttribute)currentObj).setDataType((IClassifier)child);
		}
	}

}

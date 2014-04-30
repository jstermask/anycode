package in.labulle.anycode.xmi.parser.jdom.uml;

import org.jdom2.Element;

import in.labulle.anycode.uml.Cardinality;
import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IRelationAttribute;
import in.labulle.anycode.uml.Visibility;
import in.labulle.anycode.uml.impl.ARelationAttribute;
import in.labulle.anycode.xmi.parser.jdom.util.ParserUtil;

public class IRelationAttributeParser extends IElementParser<IRelationAttribute> {



	public IRelationAttributeParser(IParserContext ctx) {
		super(ctx);
	}

	public boolean matches(IParserContext ctx) {
		return ParserUtil.valueEquals(ctx, "type", "Property") && getParserContext().getCurrentElement().getAttribute("association") != null;
	}

	@Override
	protected  IRelationAttribute newInstance() {
		return new ARelationAttribute();
	}

	@Override
	protected boolean init(IRelationAttribute obj) {
		if(obj instanceof ARelationAttribute) {
			ARelationAttribute a = ( ARelationAttribute)obj;
			a.setName(getParserContext().getElementName());
			a.setVisibility(Visibility.valueOf(getParserContext().getElementVisibility().toUpperCase()));
			a.setCardinality(calculateCardinality());
			IClassifier dt = getDatatype();
			if(dt != null) {
				a.setDataType(dt);
				return true;
			}
		}
		return false;
	}
	

	@Override
	protected void attachChild(IRelationAttribute currentObj, Object child) {
		if(child instanceof Cardinality) {
			((ARelationAttribute)currentObj).setCardinality((Cardinality)child);
		}
	}
	
	protected IClassifier getDatatype() {
		return (IClassifier)getParserContext().getParsedElements().get(getParserContext().getElementDataType());
	}
	
	private Cardinality calculateCardinality() {
		String l = null;
		String u = null;
		Element lower = getParserContext().getCurrentElement().getChild("lowerValue");
		Element upper = getParserContext().getCurrentElement().getChild("upperValue");
		
		if(lower != null) {
			l = lower.getAttributeValue("value");
		}
		if(upper != null) {
			u = upper.getAttributeValue("value");
		}
		return Cardinality.fromRange(l, u);
	}

}

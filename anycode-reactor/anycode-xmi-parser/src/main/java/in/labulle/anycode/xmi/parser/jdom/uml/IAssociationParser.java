package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.uml.IRelationAttribute;
import in.labulle.anycode.uml.impl.ARelationAttribute;
import in.labulle.anycode.xmi.parser.jdom.util.ParserUtil;

public class IAssociationParser extends IElementParser<IRelationAttribute[]> {

	public IAssociationParser(IParserContext ctx) {
		super(ctx);
	}

	public boolean matches(IParserContext ctx) {
		return ParserUtil.valueEquals(ctx, "type", "Association");
	}

	@Override
	protected IRelationAttribute[] newInstance() {
		String[] potentialRelationAttributeIds = getParserContext().getElementMemberEnd();
		IRelationAttribute e1 = (IRelationAttribute) getParserContext().getParsedElements().get(potentialRelationAttributeIds[0]);
		IRelationAttribute e2 = (IRelationAttribute) getParserContext().getParsedElements().get(potentialRelationAttributeIds[0]);
		return new IRelationAttribute[] { e1, e2 };

	}

	@Override
	protected boolean init(IRelationAttribute[] obj) {
		if(obj.length == 2 && obj[0] != null && obj[1] != null) {
			ARelationAttribute r1 = (ARelationAttribute)obj[0];
			ARelationAttribute r2 = (ARelationAttribute)obj[1];
			r1.setOtherSide(r2);
			r2.setOtherSide(r1);
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void attachChild(IRelationAttribute[] currentObj, Object child) {

	}


}

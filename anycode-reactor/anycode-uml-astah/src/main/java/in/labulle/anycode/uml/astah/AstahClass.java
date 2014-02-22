package in.labulle.anycode.uml.astah;

import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClass;

import java.util.ArrayList;
import java.util.List;

public class AstahClass extends
		AstahElement<com.change_vision.jude.api.inf.model.IClass> implements
		IClass {

	public AstahClass(com.change_vision.jude.api.inf.model.IClass astahElt) {
		super(astahElt);
	}

	public List<IAttribute> getAttributes() {
		List<IAttribute> atts = new ArrayList<IAttribute>();
		for (com.change_vision.jude.api.inf.model.IAttribute att : getAstahElement()
				.getAttributes()) {
			if (att.getAssociation() != null) {
				atts.add(new AstahRelationAttribute(att));
			} else {
				atts.add(new AstahAttribute(att));
			}
		}
		return atts;
	}

}

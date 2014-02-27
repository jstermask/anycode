package in.labulle.anycode.uml.astah;

import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClass;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AstahClass extends
		AstahElement<com.change_vision.jude.api.inf.model.IClass> implements
		IClass {
	private static final Logger LOG = LoggerFactory.getLogger(AstahClass.class);

	public AstahClass(com.change_vision.jude.api.inf.model.IClass astahElt) {
		super(astahElt);
		if(LOG.isDebugEnabled()) {
			LOG.debug("New class :" + astahElt.getName());
		}
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

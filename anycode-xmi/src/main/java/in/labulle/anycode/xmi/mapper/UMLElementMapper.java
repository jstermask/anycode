package in.labulle.anycode.xmi.mapper;

import in.labulle.anycode.api.UMLElement;
import in.labulle.anycode.xmi.XmiTag;

import org.xml.sax.Attributes;

public class UMLElementMapper {
	protected static final String XMI_ID = "xmi.id";
	protected static final String XMI_REFID = "xmi.idref";

	protected static final String NAME = "name";

	protected static final String IS_LEAF = "isLeaf";
	protected static final String IS_ROOT = "isRoot";
	protected static final String IS_ABSTRACT = "isAbstract";

	public UMLElement map(XmiTag tag, UMLElement owner, Attributes atts) {
		UMLElement item = getOrCreateElement(tag, owner, atts);
		
	
		String name = atts.getValue(NAME);
		String root = atts.getValue(IS_ROOT);
		String leaf = atts.getValue(IS_LEAF);
		String isAbstract = atts.getValue(IS_ABSTRACT);
		
		if(name != null) {
			item.setName(name);
		}
		
		if(root != null) {
			item.setRoot(Boolean.parseBoolean(root));
		}
		
		if(leaf != null) {
			item.setLeaf(Boolean.parseBoolean(leaf));
		}
		
		if(isAbstract != null) {
			item.setAbstract(Boolean.parseBoolean(isAbstract));
		}
		
		return item;
	}

	protected UMLElement getOrCreateElement(XmiTag tag, UMLElement owner,
			Attributes atts) {
		String id = atts.getValue(XMI_ID);
		if (id == null) {
			id = atts.getValue(XMI_REFID);
		}
		if (id != null) {
			UMLElement elt = null;
			if(owner != null) {
				elt = owner.getRoot().findById(id);
			}
			if (elt == null) {
				elt = (UMLElement) tag.getNewUMLInstance();
				elt.setId(id);
				elt.setOwner(owner);
			}
			return elt;
		} else {
			return null;
		}
	}
}


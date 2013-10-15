package in.labulle.anycode.xmi.mapper;

import in.labulle.anycode.api.UMLElement;

import org.xml.sax.Attributes;

public class UMLElementMapper {
	protected static final String XMI_ID = "xmi.id";
	protected static final String XMI_REFID = "xmi.refid";
	

	protected static final String NAME = "name";
	
	protected static final String IS_LEAF = "isLeaf";
	protected static final String IS_ROOT = "isRoot";
	protected static final String IS_ABSTRACT = "isAbstract";
	

	public void map(UMLElement item, UMLElement owner, Attributes atts) {
		item.setId(atts.getValue(XMI_ID));
		item.setName(atts.getValue(NAME));
		item.setRoot(Boolean.parseBoolean(atts.getValue(IS_ROOT)));
		item.setLeaf(Boolean.parseBoolean(atts.getValue(IS_LEAF)));
		item.setAbstract(Boolean.parseBoolean(atts.getValue(IS_ABSTRACT)));
		item.setOwner(owner);
	}
}

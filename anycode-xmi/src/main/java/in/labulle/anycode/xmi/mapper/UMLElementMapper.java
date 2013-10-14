package in.labulle.anycode.xmi.mapper;

import in.labulle.anycode.api.UMLElement;

import org.xml.sax.Attributes;

public class UMLElementMapper {
	protected static final String XMI_ID = "xmi.id";

	protected static final String NAME = "name";

	public void map(UMLElement item, UMLElement owner, Attributes atts) {
		item.setId(atts.getValue(XMI_ID));
		item.setName(atts.getValue(NAME));
		item.setOwner(owner);
	}
}

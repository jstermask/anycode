package in.labulle.anycode.xmi.mapper;

import in.labulle.anycode.api.UMLElement;
import in.labulle.anycode.xmi.XmiTag;

import org.xml.sax.Attributes;

public class UMLMapperFactory {

	public static UMLElement mapItem(UMLElement owner, XmiTag tag, Attributes atts) {

		try {
			UMLElement item = (UMLElement) tag.getUmlClass().newInstance();
			UMLElementMapper mapper = tag.getMapperInstance();
			mapper.map(item, owner, atts);
			return item;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

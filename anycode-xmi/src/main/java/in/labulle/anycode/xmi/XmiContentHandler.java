package in.labulle.anycode.xmi;

import in.labulle.anycode.api.UMLElement;
import in.labulle.anycode.api.UMLModel;
import in.labulle.anycode.xmi.mapper.UMLMapperFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class XmiContentHandler implements ContentHandler {
	private UMLElement umlElement;
	
	private static final Logger LOG = LoggerFactory.getLogger(XmiContentHandler.class);
	
	private boolean done = false;

	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {

		XmiTag tag = XmiTag.fromString(localName);
		if (tag != null && !done) {
			UMLElement element = UMLMapperFactory
					.mapItem(umlElement, tag, atts);
			this.umlElement = element;
			if(LOG.isDebugEnabled()) {
				LOG.debug("startElement " + umlElement);
			}
		}

	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		XmiTag tag = XmiTag.fromString(localName);
		if(XmiTag.MODEL.equals(tag)) {
			done = true;
		}
		
		if (tag != null && umlElement != null
				&& tag.getUmlClass().equals(umlElement.getClass()) && !(this.umlElement instanceof UMLModel)) {
			this.umlElement = this.umlElement.getOwner();
			if(LOG.isDebugEnabled()) {
				LOG.debug("endElement " + umlElement);
			}
		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

	public UMLModel getUmlModel() throws IllegalAccessException {
		if (umlElement instanceof UMLModel) {
			return (UMLModel) umlElement;
		} else {
			throw new IllegalAccessException(
					"getUmlModel() was called whereas current element is "
							+ (umlElement != null ? umlElement.getClass()
									.getName() : "null")
							+ " check that parser has finished its job and if the xmi file is nor invalid nor empty.");
		}
	}

}

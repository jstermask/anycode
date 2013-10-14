package in.labulle.anycode.xmi;

import in.labulle.anycode.api.UMLModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmiParser {
	private XmiContentHandler handler;

	public XmiParser() {

	}

	public void setHandler(XmiContentHandler handler) {
		this.handler = handler;
	}

	public ContentHandler getHandler() {
		return handler;
	}

	public UMLModel parse(String filename) throws IOException {
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(handler);
			InputSource source = new InputSource(new FileInputStream(new File(
					filename)));
			reader.parse(source);
			return handler.getUmlModel();
		} catch (Exception e) {
			throw new IOException(e);
		}
	}
}

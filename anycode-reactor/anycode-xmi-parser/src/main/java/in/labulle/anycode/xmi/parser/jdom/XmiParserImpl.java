package in.labulle.anycode.xmi.parser.jdom;

import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.uml.impl.AModel;
import in.labulle.anycode.xmi.parser.XmiParser;
import in.labulle.anycode.xmi.parser.XmiParserException;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

public class XmiParserImpl implements XmiParser {

	private String xmiSource;

	public XmiParserImpl(final String xmiSource) {
		this.xmiSource = xmiSource;
	}

	public IModel parse() {
		try {
			SAXBuilder jdomBuilder = new SAXBuilder();
			Document jdomDocument = jdomBuilder.build(xmiSource);
			AModel model = new AModel();
			return model;
		} catch (Exception e) {
			throw new XmiParserException(e);
		}
	}

}

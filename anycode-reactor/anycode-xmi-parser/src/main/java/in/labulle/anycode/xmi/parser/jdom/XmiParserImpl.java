package in.labulle.anycode.xmi.parser.jdom;

import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.xmi.parser.XmiParser;
import in.labulle.anycode.xmi.parser.XmiParserException;
import in.labulle.anycode.xmi.parser.XmiVersion;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

public class XmiParserImpl implements XmiParser<IModel> {

	private String xmiSource;

	public XmiParserImpl(final String xmiSource) {
		this.xmiSource = xmiSource;
	}

	public IModel parse() {
		try {
			SAXBuilder jdomBuilder = new SAXBuilder();
			Document jdomDocument = jdomBuilder.build(xmiSource);
			Element root = jdomDocument.getRootElement();
			IModelParser m = new IModelParser(new ParserContext(discoverUmlNamespace(jdomDocument), discoverXmiNamespace(jdomDocument), root));
			return m.parse();
		} catch (Exception e) {
			throw new XmiParserException(e);
		}
	}

	protected Namespace discoverUmlNamespace(Document doc) {
		for (Namespace n : doc.getRootElement().getNamespacesInScope()) {
			for (XmiVersion v : XmiVersion.values()) {
				if (n.equals(Namespace.getNamespace(v.getUmlNamespace()))) {
					return n;
				}
			}
		}
		throw new XmiParserException("UML 2.1+ namespace could not be read from the given xmi file");
	}

	protected Namespace discoverXmiNamespace(Document doc) {
		for (Namespace n : doc.getRootElement().getNamespacesInScope()) {
			for (XmiVersion v : XmiVersion.values()) {
				if (n.equals(Namespace.getNamespace(v.getXmiNamespace()))) {
					return n;
				}
			}
		}
		throw new XmiParserException("UML 2.1+ namespace could not be read from the given xmi file");
	}

}

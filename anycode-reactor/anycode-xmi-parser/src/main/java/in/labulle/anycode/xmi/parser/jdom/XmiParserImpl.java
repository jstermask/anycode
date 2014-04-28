package in.labulle.anycode.xmi.parser.jdom;

import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.xmi.core.UmlVersion;
import in.labulle.anycode.xmi.core.XmiVersion;
import in.labulle.anycode.xmi.parser.IXmiContextParser;
import in.labulle.anycode.xmi.parser.IXmiContextParserFactory;
import in.labulle.anycode.xmi.parser.exception.XmiParserException;
import in.labulle.anycode.xmi.parser.jdom.uml.IParserContext;
import in.labulle.anycode.xmi.parser.jdom.uml.ParserContext;
import in.labulle.anycode.xmi.parser.jdom.uml.XmiContextParserFactory;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

public class XmiParserImpl {

	private String xmiSource;

	public XmiParserImpl(final String xmiSource) {
		this.xmiSource = xmiSource;
	}

	@SuppressWarnings("unchecked")
	public IModel parse() {
		try {
			SAXBuilder jdomBuilder = new SAXBuilder();
			Document jdomDocument = jdomBuilder.build(xmiSource);
			Element root = jdomDocument.getRootElement();
			IXmiContextParserFactory factory = new XmiContextParserFactory();
			IParserContext ctx = new ParserContext(discoverUmlNamespace(jdomDocument), discoverXmiNamespace(jdomDocument), root);
			IXmiContextParser<IModel> parser = (IXmiContextParser<IModel>)factory.newInstance(ctx);
			return parser.parse(ctx);
		} catch (Exception e) {
			throw new XmiParserException(e);
		}
	}

	protected Namespace discoverUmlNamespace(Document doc) {
		for (Namespace n : doc.getRootElement().getNamespacesInScope()) {
			for (UmlVersion v : UmlVersion.values()) {
				if (n.equals(Namespace.getNamespace(v.getNamespace()))) {
					return n;
				}
			}
		}
		throw new XmiParserException("UML 2.1+ namespace could not be read from the given xmi file");
	}

	protected Namespace discoverXmiNamespace(Document doc) {
		for (Namespace n : doc.getRootElement().getNamespacesInScope()) {
			for (XmiVersion v : XmiVersion.values()) {
				if (n.equals(Namespace.getNamespace(v.getNamespace()))) {
					return n;
				}
			}
		}
		throw new XmiParserException("UML 2.1+ namespace could not be read from the given xmi file");
	}

}

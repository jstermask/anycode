package in.labulle.anycode.editor.persistence.xml;

import in.labulle.anycode.editor.core.Directive;
import in.labulle.anycode.editor.persistence.IDirectiveDao;

import java.io.File;
import java.io.FileOutputStream;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XmlDirectiveDao implements IDirectiveDao {

	@Override
	public Directive newDirective() {
		return new XmlDirective();
	}

	@Override
	public void saveToFile(Directive d, String outputPath) {
		Document doc = new Document(((XmlDirective) d).toXml());
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		try {
			outputter.output(doc, new FileOutputStream(new File(outputPath)));
		} catch (Exception e) {
			throw new RuntimeException("saveToFile", e);
		}
	}

	@Override
	public Directive loadFromFile(String path) {
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(new File(path));
			return new XmlDirective(doc.getRootElement());
		} catch (Exception e) {
			throw new RuntimeException("loadFromFile", e);
		}
	}

}

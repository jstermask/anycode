package in.labulle.anycode.xmi.parser.jdom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.uml.IPackage;
import in.labulle.anycode.xmi.parser.exception.XmiParserException;

import java.util.List;

import org.junit.Test;

public class XmiParserImplTest {

	@Test
	public void testSample01Model() {
		String file = getClass().getResource("/sample01-UML-2.1.1.xmi").getFile();
		XmiParserImpl parser = new XmiParserImpl(file);
		IModel model = parser.parse();
		assertEquals(1, model.getPackages().size());
		assertEquals(1, model.getClassifiers().size());
		assertEquals("Car", model.getClassifiers().get(0).getName());
		assertEquals("in", model.getPackages().get(0).getName());
		assertEquals("labulle", model.getPackages().get(0).getSubPackages().get(0).getName());
		IPackage p = model.getPackages().get(0).getSubPackages().get(0).getSubPackages().get(0);
		assertEquals("anycode", p.getName());
		List<IClassifier> classifiers = p.getClassifiers();
		assertEquals(3, classifiers.size());
		
		IClass c1 = (IClass) classifiers.get(0);
		assertEquals("Person", c1.getName());
		assertEquals(4, c1.getAttributes().size());

	}

	@Test(expected = XmiParserException.class)
	public void testSample2NoXmlFile() {
		String file = getClass().getResource("/sample2.zargo").getFile();
		XmiParserImpl parser = new XmiParserImpl(file);
		assertNotNull(parser.parse());
	}

}

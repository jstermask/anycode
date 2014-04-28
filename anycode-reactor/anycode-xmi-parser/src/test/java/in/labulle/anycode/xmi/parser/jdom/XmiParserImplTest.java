package in.labulle.anycode.xmi.parser.jdom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.xmi.parser.exception.XmiParserException;

import org.junit.Test;

public class XmiParserImplTest {

	@Test
	public void testSample01Model() {
		String file = getClass().getResource("/sample01-UML-2.1.1.xmi").getFile();
		XmiParserImpl parser = new XmiParserImpl(file);
		IModel model = parser.parse();
		assertEquals(1, model.getPackages().size());
	}

	@Test(expected = XmiParserException.class)
	public void testSample2NoXmlFile() {
		String file = getClass().getResource("/sample2.zargo").getFile();
		XmiParserImpl parser = new XmiParserImpl(file);
		assertNotNull(parser.parse());
	}

}

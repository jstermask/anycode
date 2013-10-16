package in.labulle.anycode.xmi;

import static org.junit.Assert.*;

import in.labulle.anycode.api.UMLElement;
import in.labulle.anycode.api.UMLModel;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class XmiParserTest {
	private XmiParser parser;
	
	@Before
	public void beforeTest() {
		parser = new XmiParser();
		parser.setHandler(new XmiContentHandler());
	}

	@Test
	public void testParse() throws IOException {
		UMLModel model = parser.parse(getClass().getResource("/greco.xml").getFile());
		assertNotNull(model);
		for(UMLElement elt : model.getOwnedElements()) {
			System.out.println(elt.getClass().getName() + " - " + elt.getId() + " - " + elt.getName());
		}
		assertEquals(2, model.getOwnedElements().size());
	}
	
	@Test(expected = IOException.class)
	public void testParseNoFile() throws IOException {
		parser.parse("/grec0.xml");
		
	}

}

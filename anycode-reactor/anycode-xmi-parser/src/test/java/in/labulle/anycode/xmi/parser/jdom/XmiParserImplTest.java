package in.labulle.anycode.xmi.parser.jdom;

import static org.junit.Assert.*;
import in.labulle.anycode.xmi.parser.XmiParser;
import in.labulle.anycode.xmi.parser.XmiParserException;

import org.junit.Test;

public class XmiParserImplTest {

	@Test
	public void testSample1ModelNotNull() {
		String file = getClass().getResource("/sample1.xmi").getFile();
		XmiParser parser = new XmiParserImpl(file);
		assertNotNull(parser.parse());
	}
	
	@Test(expected=XmiParserException.class)
	public void testSample2NoXmlFile() {
		String file = getClass().getResource("/sample2.zargo").getFile();
		XmiParser parser = new XmiParserImpl(file);
		assertNotNull(parser.parse());
	}

}

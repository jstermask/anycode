package in.labulle.anycode.xmi;

import static org.junit.Assert.*;

import in.labulle.anycode.api.UMLAttribute;
import in.labulle.anycode.api.UMLClass;
import in.labulle.anycode.api.UMLElement;
import in.labulle.anycode.api.UMLModel;
import in.labulle.anycode.api.UMLVisibility;

import java.beans.Visibility;
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
        UMLModel model = getModel("/greco.xml");
        assertNotNull(model);
        assertEquals(2, model.getOwnedElements().size());

        UMLClass aClass = (UMLClass) model.findById("gf-hlmcbbp3-yw7mh5-16fomc-9b1d387e11bd9ab06673c655eb4c8f66");
        assertEquals("GarageRecommande", aClass.getName());
    }

    @Test
    public void testParseSimpleModel() throws IOException {
        UMLModel model = getModel("/simplemodel01.xmi");
        assertNotNull(model);
        assertEquals(3, model.getOwnedElements().size());
        UMLClass aClass = (UMLClass) model.findById("56--64-64-34-5add2b65:141c63b3cf4:-8000:0000000000001328");
        assertEquals("TestClass", aClass.getName());
        assertEquals(2, aClass.getAttributes().size());
        UMLAttribute att1 = aClass.getAttributes().get(0);
        assertEquals("id", att1.getName());
        assertTrue(att1.getVisibility().equals(UMLVisibility.PRIVATE));

    }

    private UMLModel getModel(String resource) throws IOException {
        return parser.parse(getClass().getResource(resource).getFile());
    }

    @Test(expected = IOException.class)
    public void testParseNoFile() throws IOException {
        parser.parse("/grec0.xml");

    }

}

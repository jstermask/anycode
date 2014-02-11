package in.labulle.anycode.editor.persistence.xml;

import static org.junit.Assert.*;
import in.labulle.anycode.editor.core.Directive;
import in.labulle.anycode.editor.core.Macro;
import in.labulle.anycode.editor.persistence.IDirectiveDao;

import org.junit.Test;

public class XmlDirectiveDaoTest {
	IDirectiveDao dao = new XmlDirectiveDao();

	@Test
	public void testLoadFromFileSize() {
		Directive d = dao.loadFromFile(getClass().getResource("java-java-module.xml").getFile());
		assertNotNull(d);
		assertEquals(8, d.getElements().size());
		assertEquals("This directive provides macros and functions to generate code in java language.", d.getDescription());
		Macro m = (Macro) d.getElements().get(0);
		assertEquals("attribute", m.getName()); 
	}

}

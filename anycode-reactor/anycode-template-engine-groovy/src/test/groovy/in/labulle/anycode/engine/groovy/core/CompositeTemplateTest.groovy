package in.labulle.anycode.engine.groovy.core;

import static org.junit.Assert.*;
import in.labulle.anycode.engine.groovy.core.CompositeTemplate;

import org.junit.Test;

class CompositeTemplateTest {

	@Test
	public void testLoadDirectives() {
		CompositeTemplate ct = new CompositeTemplate();
		Map<String, Object> map = new HashMap<String, Object>();
		ct.loadDirectives(map);
		assertNotNull(map.get("java"));
		assertNotNull(map.get("jpa"));
		assertNotNull(map.get("util"));
		
	}

}

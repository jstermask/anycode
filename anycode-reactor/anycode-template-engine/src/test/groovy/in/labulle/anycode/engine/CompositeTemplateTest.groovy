package in.labulle.anycode.engine;

import static org.junit.Assert.*;

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

package in.labulle.anycode.astah.plugin.model.util;

import in.labulle.anycode.astah.api.ClassMock;
import in.labulle.anycode.uml.astah.utils.ModelUtils;

import org.junit.Assert;
import org.junit.Test;


public class ModelUtilsTest {



	@Test
	public void testIsJREClass() {
		ClassMock cl = new ClassMock("fr.test");
		cl.setName("TestClass");
		Assert.assertFalse(ModelUtils.isJREClass(cl));

		cl = new ClassMock("java.util");
		cl.setName("ArrayList");
		Assert.assertTrue(ModelUtils.isJREClass(cl));
	}

}

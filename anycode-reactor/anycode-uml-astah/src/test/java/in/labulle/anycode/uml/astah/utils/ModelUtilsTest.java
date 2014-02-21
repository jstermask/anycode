package in.labulle.anycode.uml.astah.utils;

import org.junit.Assert;
import org.junit.Test;



public class ModelUtilsTest {



	@Test
	public void testIsJREClass() {
		Assert.assertFalse(ModelUtils.isJREClass("fr.test.TestClass"));
		Assert.assertTrue(ModelUtils.isJREClass("java.util.ArrayList"));
	}

}

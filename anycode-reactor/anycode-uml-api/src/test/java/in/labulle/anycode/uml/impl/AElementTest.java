package in.labulle.anycode.uml.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AElementTest {

	@Test
	public void testFullyQualifiedName() {

		APackage p2 = new APackage();
		p2.setName("in");

		APackage p1 = new APackage();
		p1.setName("labulle");
		p1.setOwner(p2);

		APackage p = new APackage();
		p.setName("test");
		p.setOwner(p1);

		AClass cl = new AClass();
		cl.setName("MyClass");
		cl.setOwner(p);

		assertEquals("in.labulle.test.MyClass", cl.getFullyQualifiedName("."));
		assertEquals("in.labulle.test", cl.getOwner()
				.getFullyQualifiedName("."));

	}

}

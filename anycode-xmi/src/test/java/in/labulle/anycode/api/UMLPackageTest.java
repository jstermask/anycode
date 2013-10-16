package in.labulle.anycode.api;

import static org.junit.Assert.*;

import org.junit.Test;

public class UMLPackageTest {

	@Test
	public void testfindById() {
		UMLModel model = new UMLModel();
		UMLClass aClass = new UMLClass();
		aClass.setName("Person");
		aClass.setId("ID:01:01");
		UMLElement somepackage = new UMLPackage();
		somepackage.setName("somepackage");
		somepackage.setId("ID:02:02");
		UMLElement test = new UMLPackage();
		test.setName("test");
		test.setId("ID:03:03");
		UMLElement org = new UMLPackage();
		org.setName("org");
		org.setId("ID:04:04");
		org.setOwner(model);
		test.setOwner(org);
		somepackage.setOwner(test);
		aClass.setOwner(somepackage);
		
		assertEquals("Person", model.findById("ID:01:01").getName());
		assertNull(model.findById("ID:01:02"));
	
	}

}

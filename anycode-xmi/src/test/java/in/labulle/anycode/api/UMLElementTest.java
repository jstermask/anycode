package in.labulle.anycode.api;

import static org.junit.Assert.*;

import org.junit.Test;

public class UMLElementTest {

	@Test
	public void testGetFullNamespace() {
		UMLModel model = new UMLModel();
		UMLClass aClass = new UMLClass();
		aClass.setName("Person");
		UMLElement somepackage = new UMLPackage();
		somepackage.setName("somepackage");
		UMLElement test = new UMLPackage();
		test.setName("test");
		UMLElement org = new UMLPackage();
		org.setName("org");
		
		org.setOwner(model);
		test.setOwner(org);
		somepackage.setOwner(test);
		aClass.setOwner(somepackage);
		
		assertEquals("org/test", somepackage.getFullNamespace("/"));
		assertEquals("org.test.somepackage", aClass.getFullNamespace("."));
	
	}

}

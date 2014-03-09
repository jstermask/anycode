package in.labulle.anycode.engine.groovy.core;

import static org.junit.Assert.*;
import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.impl.AAttribute;
import in.labulle.anycode.uml.impl.AClass;

import org.junit.Before;
import org.junit.Test;

public class JpaDirectiveTest {
	def aClass

	@Before
	def void initClass() {
		def cl = new AClass()
		cl.setName("Person")
		def att = new AAttribute()
		att.setName("name")
		cl.addAttribute(att)
		att = new AAttribute()
		att.setName("firstname")
		cl.addAttribute(att)
		
		
		aClass = cl
	}

	@Test
	def void testAutoPrimaryKey() {
		assertEquals("", JpaDirective.primaryKey(aClass))
	}

}

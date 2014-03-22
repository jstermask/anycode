package in.labulle.anycode.engine.groovy.core;

import static org.junit.Assert.*
import in.labulle.anycode.engine.groovy.directive.JpaDirective
import in.labulle.anycode.uml.Visibility
import in.labulle.anycode.uml.impl.AAttribute
import in.labulle.anycode.uml.impl.AClass
import in.labulle.anycode.uml.impl.AStereotype

import org.junit.Before
import org.junit.Test

public class JpaDirectiveTest {
	def aClass
	
	def jpa = new JpaDirective()

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
		assertTrue(jpa.primaryKey(aClass).toString().indexOf("@javax.persistence.Id") != -1)
	}
	
	@Test
	def void testSinglePrimaryKey() {
		def att = new AAttribute()
		att.setVisibility(Visibility.PRIVATE)
		att.addStereotype(new AStereotype("id"))
		def dtcl = new AClass()
		dtcl.setName("java.lang.String")
		att.setDataType(dtcl)
		att.setName("code")
		aClass.addAttribute(att)
		
		assertTrue(att.hasStereotype("id"))
		
		assertTrue(jpa.primaryKey(aClass).toString().indexOf("@javax.persistence.Id") != -1)
		assertTrue(jpa.primaryKey(aClass).toString().indexOf("private java.lang.String code;") != -1)
		
	}
	
	@Test
	def void testCompositePrimaryKey() {
		def att = new AAttribute()
		att.setVisibility(Visibility.PRIVATE)
		att.addStereotype(new AStereotype("id"))
		def dtcl = new AClass()
		dtcl.setName("java.lang.String")
		att.setDataType(dtcl)
		att.setName("code")
		aClass.addAttribute(att)
		
		att = new AAttribute()
		att.setVisibility(Visibility.PRIVATE)
		att.addStereotype(new AStereotype("id"))
		dtcl = new AClass()
		dtcl.setName("java.lang.Integer")
		att.setDataType(dtcl)
		att.setName("secondCode")
		aClass.addAttribute(att)
		

		
		assertTrue(jpa.primaryKey(aClass).toString().indexOf("@javax.persistence.EmbeddedId") != -1)
		assertTrue(jpa.primaryKey(aClass).toString().indexOf("PersonPK id") != -1)
		
	}

}

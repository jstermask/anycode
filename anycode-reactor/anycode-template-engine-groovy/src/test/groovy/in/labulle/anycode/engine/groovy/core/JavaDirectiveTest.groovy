package in.labulle.anycode.engine.groovy.core;

import static org.junit.Assert.*
import in.labulle.anycode.engine.groovy.core.JavaDirective
import in.labulle.anycode.uml.Cardinality
import in.labulle.anycode.uml.Visibility
import in.labulle.anycode.uml.impl.AAttribute
import in.labulle.anycode.uml.impl.AClass
import in.labulle.anycode.uml.impl.ADataType

import org.junit.Test

class JavaDirectiveTest {

	@Test
	public void testGetAttributeName() {
		AAttribute att = new AAttribute()
		att.setCardinality(Cardinality.ONE_TO_ONE)
		att.setVisibility(Visibility.PRIVATE);
		AClass cl = new AClass()
		cl.setName("Person")
		att.setOwner(cl)
	
		cl = new AClass()
		cl.setName("Car")
		ADataType dt = new ADataType()
		dt.setClassifier(cl)
		att.setDataType(dt)
		assertEquals("car", JavaDirective.getAttributeName(att))
		
		att.setCardinality(Cardinality.ZERO_TO_MANY)
		assertEquals("cars", JavaDirective.getAttributeName(att))
		
		assertEquals("private java.util.List<Car> cars;", JavaDirective.attribute(att).toString())
		
	}
	
	@Test
	public void testDatatype() {
		AAttribute att = new AAttribute()
		att.setCardinality(Cardinality.ONE_TO_ONE)
		AClass cl = new AClass()
		cl.setName("Person")
		att.setOwner(cl)
	
		cl = new AClass()
		cl.setName("Car")
		ADataType dt = new ADataType()
		
		dt.setClassifier(cl)
		att.setDataType(dt)
		assertEquals("Car", JavaDirective.datatype(att).toString())
		
		att.setCardinality(Cardinality.ONE_TO_MANY)
		assertEquals("java.util.List<Car>", JavaDirective.datatype(att).toString())
		
		assertEquals("java.util.Set<Car>", JavaDirective.datatype(att, "java.util.Set").toString())
	}

}

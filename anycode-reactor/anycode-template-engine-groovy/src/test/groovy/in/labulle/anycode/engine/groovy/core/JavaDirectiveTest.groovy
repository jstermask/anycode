package in.labulle.anycode.engine.groovy.core;

import static org.junit.Assert.*
import in.labulle.anycode.engine.groovy.directive.JavaDirective;
import in.labulle.anycode.uml.Cardinality
import in.labulle.anycode.uml.Visibility
import in.labulle.anycode.uml.impl.AAttribute
import in.labulle.anycode.uml.impl.AClass
import in.labulle.anycode.uml.impl.ADataType
import in.labulle.anycode.uml.impl.AOperation;
import in.labulle.anycode.uml.impl.AParameter;

import org.junit.Test

class JavaDirectiveTest {
	
	def java = new JavaDirective()

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
		assertEquals("car", java.getAttributeName(att))

		att.setCardinality(Cardinality.ZERO_TO_MANY)
		assertEquals("cars", java.getAttributeName(att))

		assertEquals("private java.util.List<Car> cars;", java.attribute(att).toString())
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
		assertEquals("Car", java.datatype(att).toString())

		att.setCardinality(Cardinality.ONE_TO_MANY)
		assertEquals("java.util.List<Car>", java.datatype(att).toString())

		assertEquals("java.util.Set<Car>", java.datatype(att, "java.util.Set").toString())
	}

	@Test
	public void testDatatypePrimitive() {
		AAttribute att = new AAttribute()
		att.setCardinality(Cardinality.ONE_TO_ONE)

		AClass cl = new AClass()
		cl.setName("Person")
		att.setOwner(cl)

		cl = new AClass()
		cl.setName("integer")
		ADataType dt = new ADataType()
		dt.setPrimitive(true)
		dt.setClassifier(cl)
		att.setDataType(dt)

		assertEquals("Integer", java.datatype(att).toString())
	}

	@Test
	public void testGetter() {
		AAttribute att = new AAttribute()
		att.setName("myAttribute")
		att.setCardinality(Cardinality.ONE_TO_ONE)
		ADataType dt = new ADataType()
		AClass cl = new AClass()
		cl.setName("integer")
		dt.setClassifier(cl)
		dt.setPrimitive(true)
		att.setDataType(dt)
		assertEquals("public final Integer getMyAttribute() {\n\t\t\treturn this.myAttribute;\n\t\t}", java.getter(att).toString())
	}

	@Test
	public void testSetter() {
		AAttribute att = new AAttribute()
		att.setName("myAttribute")
		att.setCardinality(Cardinality.ONE_TO_ONE)
		ADataType dt = new ADataType()
		AClass cl = new AClass()
		cl.setName("integer")
		dt.setClassifier(cl)
		dt.setPrimitive(true)
		att.setDataType(dt)
		assertEquals("public final void setMyAttribute(final Integer someMyAttribute) {\n\t\t\tthis.myAttribute = someMyAttribute;\n\t\t}", java.setter(att).toString())
	}

	@Test
	public void testOperation() {
		AOperation op = new AOperation()
		op.setName("calculate")
		op.setVisibility(Visibility.PUBLIC)
		AParameter p1 = new AParameter()
		ADataType dt1 = new ADataType()
		AClass cl1 = new AClass()
		cl1.setName("integer")
		dt1.setPrimitive(true)
		dt1.setClassifier(cl1)
		p1.setName("x")
		p1.setDataType(dt1)
		p1.setVisibility(Visibility.PUBLIC)
		op.addParameter(p1)

		p1 = new AParameter()
		dt1 = new ADataType()
		cl1 = new AClass()
		cl1.setName("string")
		dt1.setPrimitive(true)
		dt1.setClassifier(cl1)
		p1.setName("testParamText")
		p1.setDataType(dt1)
		p1.setVisibility(Visibility.PUBLIC)
		op.addParameter(p1)
		
		
		
		assertEquals("public void calculate(final Integer x, final String testParamText)", java.operationSignature(op).toString())
		assertEquals("public void calculate(final Integer x, final String testParamText) {\n\t\t}", java.operationImplementation(op).toString())
		
	}
}

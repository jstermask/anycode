package in.labulle.anycode.engine.groovy.repository;

import static org.junit.Assert.*
import in.labulle.anycode.engine.core.IMacro
import in.labulle.anycode.engine.core.ITemplate
import in.labulle.anycode.engine.repository.ITemplateRepository
import in.labulle.anycode.uml.Cardinality
import in.labulle.anycode.uml.IClass
import in.labulle.anycode.uml.impl.AAttribute
import in.labulle.anycode.uml.impl.AClass
import in.labulle.anycode.uml.impl.ADataType

import org.junit.Test

class TemplateRepositoryFactoryImplTest {

	@Test
	public void testRepositoryArtifactsSize() {
		TemplateRepositoryFactoryImpl factory = new TemplateRepositoryFactoryImpl()
		ITemplateRepository rep = factory.newInstance(getClass().getResource(".").getFile())
		def artifacts = rep.getCodeGenerationArtifacts()
		assertEquals(2, artifacts.size())
		IMacro macro = artifacts.get(1)
		ITemplate tp = artifacts.get(0)
		Map<String, Object> map = new HashMap<String, Object>()
		map.put("c", getIClass())
		assertEquals("php", macro.getVarName())
		map.put(macro.getVarName(), macro.getInstance())
		def tpValue = tp.renderAsString(map)
		assertTrue(tpValue.contains("\$coolAtt"))
	}
	
	private IClass getIClass() {
		AAttribute att = new AAttribute()
		att.setName("coolAtt")
		att.setCardinality(Cardinality.ONE_TO_ONE)
		AClass cl = new AClass()
		cl.setName("Person")
		att.setOwner(cl)

		cl = new AClass()
		cl.setName("Car")
		cl.addAttribute(att)
		ADataType dt = new ADataType()

		dt.setClassifier(cl)
		att.setDataType(dt)
		return cl
	}
}

package in.labulle.anycode.engine.groovy.repository;

import static org.junit.Assert.*
import in.labulle.anycode.engine.repository.ITemplateRepository

import org.junit.Test

class TemplateRepositoryFactoryImplTest {

	@Test
	public void testRepositoryArtifactsSize() {
		TemplateRepositoryFactoryImpl factory = new TemplateRepositoryFactoryImpl()
		ITemplateRepository rep = factory.newInstance(getClass().getResource(".").getFile())
		def artifacts = rep.getCodeGenerationArtifacts()
		assertEquals(1, artifacts.size())
	}
}

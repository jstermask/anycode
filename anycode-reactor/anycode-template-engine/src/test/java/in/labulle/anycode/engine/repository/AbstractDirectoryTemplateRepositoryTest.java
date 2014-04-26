package in.labulle.anycode.engine.repository;

import static org.junit.Assert.*;
import in.labulle.anycode.engine.core.ICodeGenerationArtifact;
import in.labulle.anycode.engine.core.TemplateScope;
import in.labulle.anycode.engine.service.util.TemplateUtils;

import java.util.List;

import org.junit.Test;

public class AbstractDirectoryTemplateRepositoryTest {
	private DirectoryTemplateRepository directoryTemplateRepository = new DirectoryTemplateRepository(AbstractDirectoryTemplateRepository.class.getClass().getResource("/tps").getFile());

	@Test
	public void testCodeGenerationArtifacts() {
		List<ICodeGenerationArtifact> artifacts = directoryTemplateRepository.getCodeGenerationArtifacts();
		assertEquals(3, artifacts.size());	
		assertEquals(1, TemplateUtils.getMacros(artifacts).size());
		assertEquals(2, TemplateUtils.getTemplates(artifacts).size());
		assertEquals(1, TemplateUtils.getTemplateByScope(TemplateUtils.getTemplates(artifacts), TemplateScope.CLASSIFIER).size());
		assertEquals(1, TemplateUtils.getTemplateByScope(TemplateUtils.getTemplates(artifacts), TemplateScope.MODEL).size());		
	}

}

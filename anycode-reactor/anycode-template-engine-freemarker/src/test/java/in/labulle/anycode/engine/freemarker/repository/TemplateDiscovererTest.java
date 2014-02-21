package in.labulle.anycode.engine.freemarker.repository;

import static org.junit.Assert.assertEquals;
import in.labulle.anycode.engine.config.Configuration;
import in.labulle.anycode.engine.core.ICodeGenerationArtifact;
import in.labulle.anycode.engine.repository.ITemplateRepository;

import java.io.IOException;
import java.util.List;

import org.junit.Test;


public class TemplateDiscovererTest {

	@Test
	public void testDiscover() throws IOException {
		Configuration.getConfiguration().put("templateDir",
				getClass().getResource("/templates").getFile());
		TemplateRepositoryFactoryImpl disco = new TemplateRepositoryFactoryImpl();
		ITemplateRepository rep = disco.newInstance(getClass().getResource("/templates").getFile());
		List<ICodeGenerationArtifact> templates = rep.getCodeGenerationArtifacts();
		assertEquals(3, templates.size());
	}




}

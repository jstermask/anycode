package in.labulle.anycode.engine.freemarker.repository;

import static org.junit.Assert.assertEquals;
import in.labulle.anycode.engine.config.Configuration;
import in.labulle.anycode.engine.core.ICodeGenerationArtifact;
import in.labulle.anycode.engine.freemarker.repository.loader.FilePathTemplateLoader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;


public class TemplateDiscovererTest {

	@Test
	public void testDiscover() throws IOException {
		Configuration.getConfiguration().put("templateDir",
				getClass().getResource("/templates").getFile());
		TemplateRepositoryImpl disco = new TemplateRepositoryImpl(new FilePathTemplateLoader(new File(getClass().getResource("/templates").getFile())));
		List<ICodeGenerationArtifact> templates = disco.getCodeGenerationArtifacts();
		assertEquals(4, templates.size());
	}



	@Test
	public void testStartStop() throws IOException {
		Configuration.getConfiguration().put("how", "this");
		Configuration.getConfiguration().put("targetDir",
				System.getProperty("java.io.tmpdir"));
		Configuration.getConfiguration().put("templateDir",
				getClass().getResource("/templates").getFile());
		Configuration.getConfiguration().put("c",
				new ElementMock("fr.test.pack", "MyClass"));
		TemplateRepositoryImpl disco = new TemplateRepositoryImpl(new FilePathTemplateLoader(new File(getClass().getResource("/templates").getFile())));
		disco.getCodeGenerationArtifacts();
	}

}

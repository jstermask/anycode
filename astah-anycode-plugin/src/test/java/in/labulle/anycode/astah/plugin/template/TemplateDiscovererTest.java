package in.labulle.anycode.astah.plugin.template;

import static org.junit.Assert.assertEquals;
import in.labulle.anycode.astah.api.ClassMock;
import in.labulle.anycode.astah.plugin.template.config.Configuration;
import in.labulle.anycode.astah.plugin.template.freemarker.loader.FilePathTemplateLoader;
import in.labulle.anycode.astah.plugin.template.repository.impl.TemplateRepositoryImpl;

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
				new ClassMock("fr.test.pack", "MyClass"));
		TemplateRepositoryImpl disco = new TemplateRepositoryImpl(new FilePathTemplateLoader(new File(getClass().getResource("/templates").getFile())));
		disco.getCodeGenerationArtifacts();
	}

}

package in.labulle.anycode.astah.plugin.template.repository;

import static org.mockito.Mockito.when;
import freemarker.template.Configuration;
import in.labulle.anycode.astah.plugin.template.api.ICodeGenerationArtifact;
import in.labulle.anycode.astah.plugin.template.exception.TemplateRuntimeException;
import in.labulle.anycode.astah.plugin.template.freemarker.loader.FilePathTemplateLoader;
import in.labulle.anycode.astah.plugin.template.freemarker.repository.TemplateRepositoryImpl;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TemplateRepositoryImplTest {

	@Mock
	private FilePathTemplateLoader ftp;

	@Mock
	private Configuration configuration;

	@Test
	public void testLoadEmpty() {
		when(ftp.load(configuration)).thenReturn(
				new ArrayList<ICodeGenerationArtifact>());
		TemplateRepositoryImpl tpi = new TemplateRepositoryImpl(ftp);
		tpi.getCodeGenerationArtifacts();
	}

}

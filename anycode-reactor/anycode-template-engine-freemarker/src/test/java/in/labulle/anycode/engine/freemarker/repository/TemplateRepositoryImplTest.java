package in.labulle.anycode.engine.freemarker.repository;

import static org.mockito.Mockito.when;
import freemarker.template.Configuration;
import in.labulle.anycode.engine.core.ICodeGenerationArtifact;
import in.labulle.anycode.engine.freemarker.repository.loader.FilePathTemplateLoader;

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

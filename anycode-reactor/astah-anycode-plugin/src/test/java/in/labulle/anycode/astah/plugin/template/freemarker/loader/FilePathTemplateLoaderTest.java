package in.labulle.anycode.astah.plugin.template.freemarker.loader;

import in.labulle.anycode.engine.exception.TemplateRuntimeException;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import freemarker.template.Configuration;

@RunWith(MockitoJUnitRunner.class)
public class FilePathTemplateLoaderTest {
    @Mock
    private Configuration configuration;

    @Test(expected = TemplateRuntimeException.class)
    public void testLoadMissingFiles() throws IOException {
        FilePathTemplateLoader fp = new FilePathTemplateLoader(new File(
                getClass().getResource("/missingfiles").getFile()));
        fp.load(configuration);
    }

}

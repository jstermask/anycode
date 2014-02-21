package in.labulle.anycode.engine.repository;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.*;

public class TemplateRepositoryFactoryImplTest {

	@Test
	public void testFreemarkerEngine() throws IOException {
		TemplateRepositoryFactoryImpl impl = new TemplateRepositoryFactoryImpl(Engine.freemarker);
		ITemplateRepository rp = (ITemplateRepository)impl.newInstance(File.createTempFile("test", "test").getParent());
		assertEquals("in.labulle.anycode.engine.freemarker.repository.TemplateRepositoryFactoryImpl$1", rp.getClass().getName());
	}

}

package in.labulle.anycode.engine.service.impl;

import static org.junit.Assert.*;
import in.labulle.anycode.engine.AbstractMocks;
import in.labulle.anycode.engine.log.GenerationStatus;
import in.labulle.anycode.engine.log.ICodeGenerationEvent;
import in.labulle.anycode.engine.log.impl.CodeGenerationLog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClassCodeGenerationServiceImplTest extends AbstractMocks {

	@Test
	public void testGenerateCodeNoClassesNoReport() {
		String templatePath = "/test";
		String outputPath = "";
		ClassCodeGenerationServiceImpl classCodeGenerationServiceImpl = new ClassCodeGenerationServiceImpl(
				getModelRepository(getModel(true)),
				getTemplateRepositoryFactory(true, false));
		classCodeGenerationServiceImpl.generateCode(templatePath, outputPath);
	}

	@Test
	public void testGenerateCodeNoClassesNoTemplatesReport() {
		String templatePath = "/test";
		String outputPath = "";
		ClassCodeGenerationServiceImpl classCodeGenerationServiceImpl = new ClassCodeGenerationServiceImpl(
				getModelRepository(getModel(true)),
				getTemplateRepositoryFactory(true, false));
		CodeGenerationLog log = new CodeGenerationLog();
		classCodeGenerationServiceImpl.attachReport(log);
		classCodeGenerationServiceImpl.generateCode(templatePath, outputPath);
		assertEquals(Integer.valueOf(0), log.getNumberOfGenerations());
		assertEquals(Integer.valueOf(0), log.getNumberOfachievedGenerations());
		assertEquals(Integer.valueOf(0), log.getProgress());
	}

	@Test
	public void testGenerateCodeClassesNoTemplatesReport() {
		String templatePath = "/test";
		String outputPath = "";
		ClassCodeGenerationServiceImpl classCodeGenerationServiceImpl = new ClassCodeGenerationServiceImpl(
				getModelRepository(getModel(true)),
				getTemplateRepositoryFactory(true, false));
		CodeGenerationLog log = new CodeGenerationLog();
		classCodeGenerationServiceImpl.attachReport(log);
		classCodeGenerationServiceImpl.generateCode(templatePath, outputPath);
		assertEquals(Integer.valueOf(0), log.getNumberOfGenerations());
		assertEquals(Integer.valueOf(0), log.getNumberOfachievedGenerations());
		assertEquals(Integer.valueOf(0), log.getProgress());
	}

	@Test
	public void testGenerateCodeClassesTemplatesReport() {
		String templatePath = "/test";
		String outputPath = "";
		ClassCodeGenerationServiceImpl classCodeGenerationServiceImpl = new ClassCodeGenerationServiceImpl(
				getModelRepository(getModel(true)),
				getTemplateRepositoryFactory(false, false));
		CodeGenerationLog log = new CodeGenerationLog();
		classCodeGenerationServiceImpl.attachReport(log);
		classCodeGenerationServiceImpl.generateCode(templatePath, outputPath);
		assertEquals(Integer.valueOf(100), log.getProgress());
		assertEquals(Integer.valueOf(3), log.getNumberOfGenerations());
		assertEquals(Integer.valueOf(3), log.getNumberOfachievedGenerations());

	}
	
	@Test
	public void testGenerateCodeClassesTemplatesExceptionReport() {
		String templatePath = "/test";
		String outputPath = "";
		ClassCodeGenerationServiceImpl classCodeGenerationServiceImpl = new ClassCodeGenerationServiceImpl(
				getModelRepository(getModel(true)),
				getTemplateRepositoryFactory(false, true));
		CodeGenerationLog log = new CodeGenerationLog();
		classCodeGenerationServiceImpl.attachReport(log);
		classCodeGenerationServiceImpl.generateCode(templatePath, outputPath);
		assertEquals(Integer.valueOf(100), log.getProgress());
		assertEquals(Integer.valueOf(3), log.getNumberOfGenerations());
		assertEquals(Integer.valueOf(3), log.getNumberOfachievedGenerations());
		for(ICodeGenerationEvent event : log.getContent()) {
			assertEquals(GenerationStatus.FAILURE, event.getStatus());
			assertTrue(event.toString().indexOf("Template") > 0);
			assertEquals("This mock throws an error", event.getDetails());
		}
	}



}

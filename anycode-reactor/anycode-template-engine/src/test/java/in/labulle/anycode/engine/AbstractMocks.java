package in.labulle.anycode.engine;

import static org.mockito.Mockito.*;
import in.labulle.anycode.engine.config.Configuration;
import in.labulle.anycode.engine.core.ICodeGenerationArtifact;
import in.labulle.anycode.engine.core.IMacro;
import in.labulle.anycode.engine.core.ITemplate;
import in.labulle.anycode.engine.core.TemplateScope;
import in.labulle.anycode.engine.exception.TemplateException;
import in.labulle.anycode.engine.repository.ITemplateRepository;
import in.labulle.anycode.engine.repository.ITemplateRepositoryFactory;
import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.uml.impl.AClass;
import in.labulle.anycode.uml.impl.APackage;
import in.labulle.anycode.uml.repository.IModelRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AbstractMocks {
	protected IModelRepository getModelRepository(IModel model) {
		IModelRepository modelRepo = mock(IModelRepository.class);
		when(modelRepo.getModelInstance()).thenReturn(model);
		return modelRepo;
	}

	protected ITemplateRepositoryFactory getTemplateRepositoryFactory(
			boolean empty, boolean templateThrowsException) {
		ITemplateRepositoryFactory templateRepositoryFactory = mock(ITemplateRepositoryFactory.class);
		ITemplateRepository templateRepository = getTemplateRepository(empty, templateThrowsException);
		when(templateRepositoryFactory.newInstance("/test")).thenReturn(
				templateRepository);
		return templateRepositoryFactory;
	}

	protected ITemplateRepository getTemplateRepository(boolean empty, boolean templateThrowsException) {
		ITemplateRepository templateRepository = mock(ITemplateRepository.class);
		List<ICodeGenerationArtifact> artifacts = Arrays.asList(
				getModelTemplate(templateThrowsException), getClassifierTemplate(templateThrowsException), getMacro());
		if (empty) {
			artifacts = new ArrayList<ICodeGenerationArtifact>();
		}
		when(templateRepository.getCodeGenerationArtifacts()).thenReturn(
				artifacts);
		return templateRepository;
	}

	protected IModel getModel(boolean withClasses) {
		IModel model = mock(IModel.class);
		if (withClasses) {
			when(model.getAllClasses()).thenReturn(
					Arrays.asList((IClassifier)getPersonClass(), (IClassifier)getCarClass()));
		} else {
			when(model.getAllClasses()).thenReturn(new ArrayList<IClassifier>());

		}
		return model;
	}

	protected IClass getPersonClass() {
		AClass c = new AClass("Person");
		c.setOwner(new APackage("test"));
		return c;
	}

	protected IClass getCarClass() {
		AClass c = new AClass("Car");
		c.setOwner(new APackage("test"));
		return c;
	}

	protected ITemplate getClassifierTemplate(boolean throwsException) {
		ITemplate t = mock(ITemplate.class);
		when(t.getScope()).thenReturn(TemplateScope.CLASSIFIER);
		when(t.getName()).thenReturn("Classifier Template");
		if (throwsException) {
			try {
				when(t.renderToFile(any(Configuration.class))).thenThrow(
						new TemplateException(new Exception(
								"This mock throws an error")));
				when(t.renderAsString(any(Configuration.class))).thenThrow(
						new TemplateException(new Exception(
								"This mock throws an error")));
				
			} catch (Exception e) {

			}
		}
		return t;
	}

	protected ITemplate getModelTemplate(boolean throwsException) {
		ITemplate t = mock(ITemplate.class);
		when(t.getScope()).thenReturn(TemplateScope.MODEL);
		when(t.getName()).thenReturn("Model Template");
		if (throwsException) {
			try {
				when(t.renderToFile(any(Configuration.class))).thenThrow(
						new TemplateException(new Exception(
								"This mock throws an error")));
				when(t.renderAsString(any(Configuration.class))).thenThrow(
						new TemplateException(new Exception(
								"This mock throws an error")));
				
			} catch (Exception e) {

			}
		}
		return t;
	}

	protected IMacro getMacro() {
		IMacro t = mock(IMacro.class);
		return t;
	}

	protected Configuration getConfiguration() {
		return Configuration.getConfiguration();
	}
}

package in.labulle.anycode.engine.repository;

import static org.mockito.Mockito.*;
import in.labulle.anycode.engine.core.IMacro;
import in.labulle.anycode.engine.core.ITemplate;
import in.labulle.anycode.engine.core.TemplateScope;

public class DirectoryTemplateRepository extends
		AbstractDirectoryTemplateRepository {

	public DirectoryTemplateRepository(String path) {
		super(path);
	}

	@Override
	protected ITemplate buildTemplate(String nameTemplatePath,
			String contentTemplatePath, TemplateScope scope) {
		ITemplate template = mock(ITemplate.class);
		when(template.getName()).thenReturn(nameTemplatePath);
		when(template.getScope()).thenReturn(scope);
		return template;
	}

	@Override
	protected IMacro buildMacro(String macroFilePath) {
		IMacro template = mock(IMacro.class);
		when(template.getName()).thenReturn(macroFilePath);
		return template;
	}

}

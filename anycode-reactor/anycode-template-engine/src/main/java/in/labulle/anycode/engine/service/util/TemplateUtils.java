package in.labulle.anycode.engine.service.util;

import in.labulle.anycode.engine.core.ICodeGenerationArtifact;
import in.labulle.anycode.engine.core.IMacro;
import in.labulle.anycode.engine.core.ITemplate;
import in.labulle.anycode.engine.core.TemplateScope;

import java.util.ArrayList;
import java.util.List;

public class TemplateUtils {
	public static List<ITemplate> getTemplateByScope(List<ITemplate> tps, TemplateScope scope) {
		List<ITemplate> tpstemps = new ArrayList<ITemplate>(tps);
		for(ITemplate tp : tps) {
			if(!tp.getScope().equals(scope)) {
				tpstemps.remove(tp);
			}
		}
		return tpstemps;
	}
	
	public static List<ITemplate> getTemplates(List<ICodeGenerationArtifact> arts) {
		List<ITemplate> templates = new ArrayList<ITemplate>();
		for (ICodeGenerationArtifact art : arts) {
			if (art instanceof ITemplate) {
				templates.add((ITemplate) art);
			}
		}
		return templates;
	}

	public static List<IMacro> getMacros(List<ICodeGenerationArtifact> arts) {
		List<IMacro> macros = new ArrayList<IMacro>();
		for (ICodeGenerationArtifact art : arts) {
			if (art instanceof IMacro) {
				macros.add((IMacro) art);
			}
		}
		return macros;
	}
}

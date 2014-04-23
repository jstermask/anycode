package in.labulle.anycode.engine.service.handler;

import in.labulle.anycode.engine.core.ITemplate;
import in.labulle.anycode.uml.IModel;

import java.util.List;

/**
 * 
 * @author Jose Carreno
 * 
 */
public interface ICodeGenerator {
	void generateCode(IModel model, List<ITemplate> templates);
	
	Integer calculateNumberOfGenerations(IModel model, List<ITemplate> templates);

}

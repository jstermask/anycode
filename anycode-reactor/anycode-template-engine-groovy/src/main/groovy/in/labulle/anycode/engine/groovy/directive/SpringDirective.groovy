package in.labulle.anycode.engine.groovy.directive

import org.codehaus.groovy.classgen.ReturnAdder;

import jline.ArgumentCompletor.AbstractArgumentDelimiter;
import in.labulle.anycode.uml.Cardinality;
import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.Visibility;
import in.labulle.anycode.uml.impl.AAttribute;
import in.labulle.anycode.uml.impl.ADataType;

class SpringDirective extends JavaDirective {
	def autowiredAttribute(IClass c) {
		AAttribute a = new AAttribute()
		a.setCardinality(Cardinality.ONE_TO_ONE)
		ADataType dt = new ADataType()
		dt.setClassifier(c)
		a.setDataType(dt)
		a.setName(a.getDataType().getName()[0].toLowerCase() + a.getDataType().getName().substring(1))
		a.setVisibility(Visibility.PRIVATE)
		return """@org.springframework.beans.factory.annotation.Autowired
			${attribute(a)}
			${getter(a)}
			${setter(a)}
		"""
	}
}

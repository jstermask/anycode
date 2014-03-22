package in.labulle.anycode.engine.groovy.directive

import in.labulle.anycode.uml.Cardinality
import in.labulle.anycode.uml.IClass
import in.labulle.anycode.uml.Visibility
import in.labulle.anycode.uml.impl.AAttribute
import in.labulle.anycode.uml.impl.AClass

class SpringDataDirective extends JavaDirective {
	def repositoryAttribute(IClass c) {
		AAttribute a = new AAttribute()
		a.setCardinality(Cardinality.ONE_TO_ONE)
		AClass cc = new AClass()
		cc.setOwner(c.getOwner())
		cc.setName(c.getName() + "Repository")
		a.setDataType(cc)
		a.setName(a.getDataType().getName()[0].toLowerCase() + a.getDataType().getName().substring(1))
		a.setVisibility(Visibility.PRIVATE)
		return """@org.springframework.beans.factory.annotation.Autowired
			${attribute(a)}
			${getter(a)}
			${setter(a)}
		"""
	}
}

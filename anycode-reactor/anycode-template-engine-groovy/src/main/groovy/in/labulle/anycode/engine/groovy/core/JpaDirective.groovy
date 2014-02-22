package in.labulle.anycode.engine.groovy.core



import java.lang.annotation.Annotation;

import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IRelationAttribute;
import groovy.text.SimpleTemplateEngine

/**
 * JPA Directive.
 * @author Jose Carreno
 *
 */
class JpaDirective extends AnycodeDirective {
	/**
	 * @param a attribute
	 * @return attribute full signature as a script : [visibility] [datatype] [attribute name]. e.g. : private String myVar;
	 */
	def static attribute(IAttribute a) {
		def script = """\
		${JpaDirective.annotation(a)}
		${JavaDirective.attribute(a)}
		"""
		return script;
	}
	
	def static annotation(IAttribute a) {
		if(!a.isRelation()) {
			return null;
		} else {
			def ar = (IRelationAttribute)a
			if(ar.isOneToOne()) {
				return "@OneToOne"
			} else if (ar.isOneToMany()) {
				return "@OneToMany"
			} else if(ar.isManyToMany()) {
				return "@ManyToMany"
			} else if(ar.isManyToOne()) {
				return "@ManyToOne"
			}
		}
	}
}
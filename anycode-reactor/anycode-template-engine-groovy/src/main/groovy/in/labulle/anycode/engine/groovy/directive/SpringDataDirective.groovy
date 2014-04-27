package in.labulle.anycode.engine.groovy.directive

import in.labulle.anycode.uml.Cardinality
import in.labulle.anycode.uml.IClass
import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.Visibility
import in.labulle.anycode.uml.impl.AAttribute
import in.labulle.anycode.uml.impl.AClass

/**
 * Spring Data Directive. It extends {@link JavaDirective} and provides method to help generate 'Repository' classes.
 * @author Jose Carreno
 *
 */
class SpringDataDirective extends JavaDirective {
	/**
	 * Renders repository attribute based on the given class. Format : 
	 * <pre>
	 * '@Autowired'
	 * private MyClassRepository myClassRepository;
	 * ... (getter and setter also generated).
	 * </pre>
	 * It is very interesting to use it within service classes to autowire ORM repositories. The typical use case is to model a Service class and add UML dependencies to your 'Entity classes'. The
	 * latter will then be accessible programmatically in the API through {@link IClassifier#getClientDependencies()}. Then for each dependency, you can call this method to autowired they repository classes. This way
	 * of modelling is very close to what <a href="http://www.andromda.org/andromda-cartridges/andromda-spring-cartridge/howto3.html" target="_blank">AndroMDA</a> does
	 * @param c class.
	 * @return Spring repository class declaration with its getter and setter as a GString.
	 */
	public def repositoryAttribute(IClass c) {
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

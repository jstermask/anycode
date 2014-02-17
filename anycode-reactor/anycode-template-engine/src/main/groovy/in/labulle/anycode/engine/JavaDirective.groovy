package in.labulle.anycode.engine



import groovy.text.SimpleTemplateEngine
import static java.lang.String.*

/**
 * JPA Directive.
 * @author Jose Carreno
 *
 */
class JavaDirective {
	/**
	 * @param a attribute
	 * @return attribute full signature as a script : [visibility] [datatype] [attribute name]. e.g. : private String myVar;
	 */
	def static attribute(a) {
		def script = """\
		${a.visibility.toString().toLowerCase()} ${a.dataType.primitive ? (a.dataType.name.capitalize()) : a.dataType.name} $a.name;
		"""
		return script;
	}
}
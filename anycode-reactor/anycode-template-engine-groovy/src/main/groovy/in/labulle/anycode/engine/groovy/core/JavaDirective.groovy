package in.labulle.anycode.engine.groovy.core



import in.labulle.anycode.uml.Cardinality;
import in.labulle.anycode.uml.IAttribute;
import groovy.text.SimpleTemplateEngine
import static java.lang.String.*

/**
 * This directive provides macros and functions to generate code in java language.
 * @author Jose Carreno
 *
 */
class JavaDirective extends AnycodeDirective {
	/**
	 * Gets or generates attribute's name
	 * @param a attribute
	 * @return attribute's name or datatype name if relation attribute has no specific name.
	 */
	def static getAttributeName(IAttribute a) {
		def attName = (a.getName() != null ? a.getName() : a.getDataType().getName()[0].toLowerCase() + a.getDataType().getName().substring(1))
		if(!a.getCardinality().isSingle()) {
			attName += "s"
		}
		return attName
	}


	/**
	 * @param a attribute
	 * @return attribute full signature as a script : [visibility] [datatype] [attribute name]. e.g. : private String myVar;
	 */
	def static attribute(IAttribute a) {
		return """${a.visibility.toString().toLowerCase()} ${datatype(a)} ${getAttributeName(a)};"""
	}

	/**
	 * renders attribute datatype. It attribute is collection, renders the specified collection type
	 * @param a attribute.
	 * @param collectionClassName (java.util.List for instance)
	 * @return
	 */
	def static datatype(IAttribute a, String collectionClassName) {
		if(a.getDataType().isPrimitive()) {
			return """${a.dataType.name.capitalize()}"""
		} else {
			return """${!a.cardinality.single ? collectionClassName + '<' + a.dataType.name + '>' : a.dataType.name}"""
		}
	}

	/**
	 * renders attribute datatype. It attribute is collection, renders a java.util.List of attributes.
	 * @param a attribute.
	 * @return
	 */
	def static datatype(IAttribute a) {
		return datatype(a, 'java.util.List')
	}
}
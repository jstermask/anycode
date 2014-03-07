package in.labulle.anycode.engine.groovy.core



import in.labulle.anycode.uml.Cardinality;
import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IDataType;
import in.labulle.anycode.uml.IInterface;
import in.labulle.anycode.uml.IOperation;
import groovy.text.SimpleTemplateEngine
import static java.lang.String.*

/**
 * This directive provides macros and functions to generate code in java language.
 * @author Jose Carreno
 *
 */
class JavaDirective extends AnycodeDirective {

	/**
	 * 
	 * @param c
	 * @return
	 */
	def static classifierSignature(IClassifier c) {
		if(c instanceof IClass) {
			return classSignature((IClass)c)
		} else {
			return interfaceSignature((IInterface)c)
		}
	}	

	/**
	 * Class signature
	 * @param c
	 * @return
	 */
	def static classSignature(IClass c) {
		def generalizations = generalizations(c)
		def realizations = realizations(c)
		return """public class ${c.name} ${generalizations != null ? 'extends '+ generalizations : ''}  ${realizations != null ? 'implements '+ realizations : ''}"""
	}
	
	/**
	 * Class signature
	 * @param i
	 * @return
	 */
	def static interfaceSignature(IInterface i) {
		def generalizations = generalizations(i)
		return """public interface ${i.name} ${generalizations != null ? 'extends '+ generalizations : ''}"""
	}
	
	/**
	 * renders generalizations
	 * @param c
	 * @return comma separated generalizations
	 */
	def static generalizations(IClassifier c) {
		def generalizations = null
		c.generalizations.reverseEach { generalizations = it.name + (generalizations == null ? "" : ", " + generalizations)}
		return generalizations;
	}
	
	/**
	 * renders realizations
	 * @param c 
	 * @return comma separated realizations
	 */
	def static realizations(IClass c) {
		def realizations = null
		c.realizations.reverseEach { realizations = it.name + (realizations == null ? "" : ", " + realizations)}
		return realizations;
	}
	
	
	/**
	 * Generates attribute's name
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
	 * Generate datatype name
	 * @param d
	 * @return
	 */
	def static getDataTypeName(IDataType d) {
		return (d.isPrimitive() ? d.getName().capitalize() : d.getName())
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
			return """${getDataTypeName(a.dataType)}"""
		} else {
			return """${!a.cardinality.single ? collectionClassName + '<' + getDataTypeName(a.dataType) + '>' : getDataTypeName(a.dataType)}"""
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
	
	/**
	 * renders attribute's getter implementation
	 * @param a attribute
	 * @return
	 */
	def static getter(IAttribute a) {
		return """public final ${datatype(a)} get${getAttributeName(a).capitalize()}() {
			return this.${getAttributeName(a)};
		}"""
	}
	
	/**
	 * renders attribute's getter implementation
	 * @param a attribute
	 * @return
	 */
	def static setter(IAttribute a) {
		return """public final void set${getAttributeName(a).capitalize()}(final ${datatype(a)} some${getAttributeName(a).capitalize()}) {
			this.${getAttributeName(a)} = some${getAttributeName(a).capitalize()};
		}"""
	}
	
	/**
	 * 
	 * @param op
	 * @return
	 */
	def static operationSignature(IOperation op) {
		def params = null
		op.getParameters().reverseEach() { it -> params = "final ${getDataTypeName(it.dataType)} ${it.name}" + (params == null ? "" : ", " + params)  }
		
		return """${op.visibility.toString().toLowerCase()} ${op.returnType == null ? 'void' : op.returnType.name} ${op.name}(${params})"""
	}
	
	/**
	 * 
	 * @param op
	 * @return
	 */
	def static operationImplementation(IOperation op) {
		return """${operationSignature(op)} {
		}"""
	}
}

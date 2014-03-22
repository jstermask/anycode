package in.labulle.anycode.engine.groovy.directive



import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IInterface;
import in.labulle.anycode.uml.IOperation;

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
	def  classifierSignature(IClassifier c) {
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
	def  classSignature(IClass c) {
		def generalizations = generalizations(c)
		def realizations = realizations(c)
		return """public class ${c.name} ${generalizations != null ? 'extends '+ generalizations : ''}  ${realizations != null ? 'implements '+ realizations : ''}"""
	}
	
	/**
	 * Class signature
	 * @param i
	 * @return
	 */
	def  interfaceSignature(IInterface i) {
		def generalizations = generalizations(i)
		return """public interface ${i.name} ${generalizations != null ? 'extends '+ generalizations : ''}"""
	}
	
	/**
	 * renders generalizations
	 * @param c
	 * @return comma separated generalizations
	 */
	def  generalizations(IClassifier c) {
		def generalizations = null
		c.generalizations.reverseEach { generalizations = it.name + (generalizations == null ? "" : ", " + generalizations)}
		return generalizations;
	}
	
	/**
	 * renders realizations
	 * @param c 
	 * @return comma separated realizations
	 */
	def  realizations(IClass c) {
		def realizations = null
		c.realizations.reverseEach { realizations = it.name + (realizations == null ? "" : ", " + realizations)}
		return realizations;
	}
	
	
	/**
	 * Generates attribute's name
	 * @param a attribute
	 * @return attribute's name or datatype name if relation attribute has no specific name.
	 */
	def  getAttributeName(IAttribute a) {
		def attName = (a.getName() != null ? a.getName() : a.getDataType().getName()[0].toLowerCase() + a.getDataType().getName().substring(1))
		if(!a.getCardinality().isSingle()) {
			if(a.getName() == null) { 
				attName += "s"
			}
		}
		return attName
	}
	
	/**
	 * Generate datatype name
	 * @param d
	 * @return
	 */
	def  getDataTypeName(IClassifier c) {
		return getDataTypeName(c, null)
	}
	
	/**
	 * Generates datatype name followed by its modifier
	 * @param c
	 * @param modifier
	 * @return
	 */
	def getDataTypeName(IClassifier c, String modifier) {
		def dt = (c.isPrimitive() ? c.getName().capitalize() : c.getName())
		if(modifier != null) {
			dt += modifier
		}
		return dt
	}


	/**
	 * @param a attribute
	 * @return attribute full signature as a script : [visibility] [datatype] [attribute name]. e.g. : private String myVar;
	 */
	def  attribute(IAttribute a) {
		return """${a.visibility.toString().toLowerCase()} ${datatype(a)} ${getAttributeName(a)};"""
	}

	/**
	 * renders attribute datatype. It attribute is collection, renders the specified collection type
	 * @param a attribute.
	 * @param collectionClassName (java.util.List for instance)
	 * @return
	 */
	def  datatype(IAttribute a, String collectionClassName) {
		if(a.getDataType().isPrimitive()) {
			return """${getDataTypeName(a.dataType, a.modifier)}"""
		} else {
			return """${!a.cardinality.single ? collectionClassName + '<' + getDataTypeName(a.dataType, a.modifier) + '>' : getDataTypeName(a.dataType, a.modifier)}"""
		}
	}

	/**
	 * renders attribute datatype. It attribute is collection, renders a java.util.List of attributes.
	 * @param a attribute.
	 * @return
	 */
	def  datatype(IAttribute a) {
		return datatype(a, 'java.util.List')
	}
	
	/**
	 * renders attribute's getter implementation
	 * @param a attribute
	 * @return
	 */
	def  getter(IAttribute a) {
		return """public final ${datatype(a)} get${getAttributeName(a).capitalize()}() {
			return this.${getAttributeName(a)};
		}"""
	}
	
	/**
	 * renders attribute's getter implementation
	 * @param a attribute
	 * @return
	 */
	def  setter(IAttribute a) {
		return """public final void set${getAttributeName(a).capitalize()}(final ${datatype(a)} some${getAttributeName(a).capitalize()}) {
			this.${getAttributeName(a)} = some${getAttributeName(a).capitalize()};
		}"""
	}
	
	/**
	 * 
	 * @param op
	 * @return
	 */
	def  operationSignature(IOperation op) {
		def params = null
		op.getParameters().reverseEach() { it -> params = "final ${getDataTypeName(it.dataType, it.modifier)} ${it.name}" + (params == null ? "" : ", " + params)  }
		
		return """${op.visibility.toString().toLowerCase()} ${op.returnType == null ? 'void' : getDataTypeName(op.returnType, op.modifier)} ${getOperationName(op)}(${params})"""
	}
	
	/**
	 * 
	 * @param op
	 * @return
	 */
	protected def  getOperationName(IOperation op) {
		return op.getName()
	}
	
	/**
	 * 
	 * @param op
	 * @return
	 */
	def  operationImplementation(IOperation op) {
		return """${operationSignature(op)} {
		}"""
	}
}

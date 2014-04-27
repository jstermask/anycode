package in.labulle.anycode.engine.groovy.directive



import groovy.lang.GString;
import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IInterface;
import in.labulle.anycode.uml.IOperation;

/**
 * This directive provides macros and functions to generate code in java language. Most of the functions return a object of {@link GString} type so that result can be used directly in the template for a runtime evaluation.
 * @author Jose Carreno
 *
 */
class JavaDirective extends AnycodeDirective {

	/**
	 * Renders classifier signature. Depending on whether it's an {@link IClass} or an {@link IInterface}, signature will be different. 
	 * (format : public class [ClassName] extends [Generalizations] implements [Realizations], or public interface [InterfaceName] extends [Generalization]).
	 * @param c classifier. must not be null.
	 * @return GString containing the class signature.
	 */
	public def classifierSignature(IClassifier c) {
		if(c instanceof IClass) {
			return classSignature((IClass)c)
		} else {
			return interfaceSignature((IInterface)c)
		}
	}

	/**
	 * Renders class signature. Format : 'public class [ClassName] extends [Generalizations] implements [Realizations]'
	 * @param c class. must not be null.
	 * @return GString of the class signature.
	 */
	private def classSignature(IClass c) {
		def generalizations = generalizations(c)
		def realizations = realizations(c)
		return """public class ${c.name} ${generalizations != null ? 'extends '+ generalizations : ''}  ${realizations != null ? 'implements '+ realizations : ''}"""
	}

	/**
	 * Renders interface signature. 'public interface [InterfaceName] extends [Generalization]'
	 * @param i interface. must not be null.
	 * @return GString of the interface signature.
	 */
	private def interfaceSignature(IInterface i) {
		def generalizations = generalizations(i)
		return """public interface ${i.name} ${generalizations != null ? 'extends '+ generalizations : ''}"""
	}

	/**
	 * Renders generalizations. See {@link IClassifier#getGeneralizations()} for further details.
	 * @param c classifier.
	 * @return comma separated list of generalization classifier names.
	 */
	public def generalizations(IClassifier c) {
		def generalizations = null
		c.generalizations.reverseEach { generalizations = it.name + (generalizations == null ? "" : ", " + generalizations)}
		return generalizations;
	}

	/**
	 * Renders realizations. See {@link IClass#getRealizations()} for further details.
	 * @param c classifier.
	 * @return comma separated list realization classifier names.
	 */
	public def realizations(IClass c) {
		def realizations = null
		c.realizations.reverseEach { realizations = it.name + (realizations == null ? "" : ", " + realizations)}
		return realizations;
	}


	/**
	 * Calculates attribute's name. In many cases, it will just take the 'name' attribute of the {@link IAttribute} class. 
	 * In case it is null a fallback algorithm will generated a name automatically, based on attribute's datatype.
	 * @param a attribute.
	 * @return attribute's name or datatype name if relation attribute has no specific name.
	 */
	public def getAttributeName(IAttribute a) {
		def attName = (a.getName() != null ? a.getName() : a.getDataType().getName()[0].toLowerCase() + a.getDataType().getName().substring(1))
		if(!a.getCardinality().isSingle()) {
			if(a.getName() == null) {
				attName += "s"
			}
		}
		return attName
	}

	/**
	 * Generate datatype name. This is mostly used to render operation return types, attribute types, parameter types. This method will just call {@link #getDataTypeName(IClassifier, String)} with null modifier.
	 * In case this classifier is primitive ({@link IClassifier#isPrimitive()} is true), then datatype name is just capitalized and returned (e.g. : string -> String)
	 * @param c classifier.
	 * @return type datatype name. This is never null.
	 */
	public def getDataTypeName(IClassifier c) {
		return getDataTypeName(c, null)
	}

	/**
	 * Generates datatype name followed by its modifier. This method does the same as {@link #getDataTypeName(IClassifier)} but adds the generation of a modifier. This is particularly used when rendered collections that 
	 * contain generics feature.
	 * @param c classifier.
	 * @param modifier String representing a modifier (e.g. :  '<MyClass>')
	 * @return String with datatype name. 
	 */
	public def getDataTypeName(IClassifier c, String modifier) {
		def dt = (c.isPrimitive() ? c.getName().capitalize() : c.getName())
		if(modifier != null) {
			dt += modifier
		}
		return dt
	}


	/**
	 * Renders an attribute. Format : '[visibility] [datatype] [attribute name];'. e.g. : private String myVar;
	 * @param a attribute to render.
	 * @return attribute full signature as a script as a GString. 
	 */
	public def attribute(IAttribute a) {
		return """${a.visibility.toString().toLowerCase()} ${datatype(a)} ${getAttributeName(a)};"""
	}

	/**
	 * Renders attribute's datatype. If attribute is a collection, renders the specified collection type in parameters.
	 * @param a attribute.
	 * @param collectionClassName type preferred for code generation ('java.util.List' for instance)
	 * @return attribute's datatype as a GString.
	 */
	public def datatype(IAttribute a, String collectionClassName) {
		if(a.getDataType().isPrimitive()) {
			return """${getDataTypeName(a.dataType, a.modifier)}"""
		} else {
			return """${!a.cardinality.single ? collectionClassName + '<' + getDataTypeName(a.dataType, a.modifier) + '>' : getDataTypeName(a.dataType, a.modifier)}"""
		}
	}

	/**
	 * Renders attribute datatype. If attribute is collection, renders a 'java.util.List' type by default. This can be customized by calling {@link #datatype(IAttribute, String)}.
	 * @param a attribute.
	 * @return attribute's datatype as a GString.
	 */
	def datatype(IAttribute a) {
		return datatype(a, 'java.util.List')
	}

	/**
	 * Renders attribute's getter implementation.
	 * @param a attribute.
	 * @return attribute's getter as a GString.
	 */
	def  getter(IAttribute a) {
		return """public final ${datatype(a)} get${getAttributeName(a).capitalize()}() {
			return this.${getAttributeName(a)};
		}"""
	}

	/**
	 * Renders attribute's setter implementation.
	 * @param a attribute.
	 * @return attribute's setter as a GString.
	 */
	def  setter(IAttribute a) {
		return """public final void set${getAttributeName(a).capitalize()}(final ${datatype(a)} some${getAttributeName(a).capitalize()}) {
			this.${getAttributeName(a)} = some${getAttributeName(a).capitalize()};
		}"""
	}

	/**
	 * Renders operation signature. format '[visibility] [returnType|void] [operation name]([params])'. Note that there is no semi colon at the end of the operation signature.
	 * @param op operation.
	 * @return operation signature as a GString.
	 */
	def  operationSignature(IOperation op) {
		def params = null
		op.getParameters().reverseEach() { it -> params = "final ${getDataTypeName(it.dataType, it.modifier)} ${it.name}" + (params == null ? "" : ", " + params)  }

		return """${op.visibility.toString().toLowerCase()} ${op.returnType == null ? 'void' : getDataTypeName(op.returnType, op.modifier)} ${getOperationName(op)}(${params})"""
	}

	/**
	 * Renders operation name. In java, we just return the 'name' attribute of the operation. Although, we prefer to use this abstration so that we can customize operation name.
	 * @param op operation.
	 * @return operation's name.
	 */
	protected def  getOperationName(IOperation op) {
		return op.getName()
	}

	/**
	 * Renders operation implementation. format '[visibility] [returnType|void] [operation name]([params]) { }'.
	 * @param op operation.
	 * @return operation implementation as a GString.
	 */
	def  operationImplementation(IOperation op) {
		return """${operationSignature(op)} {
		}"""
	}
}

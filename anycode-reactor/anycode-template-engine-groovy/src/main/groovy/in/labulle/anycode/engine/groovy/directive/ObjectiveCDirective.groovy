package in.labulle.anycode.engine.groovy.directive

import in.labulle.anycode.uml.IAttribute
import in.labulle.anycode.uml.IClassifier
import in.labulle.anycode.uml.IOperation;

class ObjectiveCDirective extends AnycodeDirective {
	/**
	 * Renders attribute's name.
	 * @param a attribute.
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
	 * Renders attribute. Format [datatype] [attribute name];
	 * @param a attribute.
	 * @return attribute declaration as a GString.
	 */
	def attribute(IAttribute a) {
		return """${datatype(a)} ${getAttributeName(a)};"""
	}
	
	/**
	 * Renders datatype's name. This is mainly used to render primitive datatype names automatically and apply a mapping to Objective-C types :
	 * <ul>
	 * 	<li>string => NSString *</li>
	 *  <li>int => NSInteger</li>
	 *  <li>float => CGFloat *</li>
	 *  <li>date => NSDate *</li>
	 *  <li>double => double</li>
	 *  <li>[MyClass] => MyClass *</li>
	 * </ul>
	 * @param d classifier
	 * @param modifier type's modifier (not used in the current implementation).
	 * @return datatype name as a String.
	 */
	def getDataTypeName(IClassifier d, String modifier) {
		def dtName = d.getName().toLowerCase()
		if(d.isPrimitive()) {
			if(dtName.indexOf("string") != -1) {
				return "NSString *"
			} else if(dtName.indexOf("int") != -1) {
				return "NSInteger"
			} else if(dtName.indexOf("float") != -1) {
				return "CGFloat *"
			} else if(dtName.indexOf("date") != -1) {
				return "NSDate *"
			} else if(dtName.indexOf("double") != -1) {
				return "double"
			} 
		} else {
			return d.getName() + " *" 
		}
	}
	
	/**
	 * Renders attribute's datatype declaration. If type is multivalued, then the given collectionClassName is used as a datatype.
	 * @param a attribute.
	 * @param collectionClassName name of the collection class (e.g. : NSArray *).
	 * @return datatype declaration as a GString.
	 */
	def datatype(IAttribute a, String collectionClassName) {
		if(a.getDataType().isPrimitive()) {
			return """${getDataTypeName(a.dataType, null)}"""
		} else {
			return """${!a.cardinality.single ? collectionClassName : getDataTypeName(a.dataType, a.modifier)}"""
		}
	}
	
	/**
	 * Renders attribute's datatype declaration. Calls the underlying {@link #datatype(IAttribute, String)} with 'NSArray *' collection class name.
	 * @param a attribute
	 * @return datatype declaration as a GString.
	 */
	def datatype(IAttribute a) {
		return datatype(a, 'NSArray *')
	}
	
	/**
	 * Renders operation name. 
	 * @param op operation.
	 * @return operation 'name' attribute.
	 */
	protected def  getOperationName(IOperation op) {
		return op.getName()
	}
	
	/**
	 * Renders operation signature. format '- [(returnType)|(void)] [operation name]([params])'. Note that there is no semi colon at the end of the operation signature.
	 * @param op operation.
	 * @return operation signature as a GString.
	 */
	def operationSignature(IOperation op) {
		def params = null
		op.getParameters().reverseEach() { it -> params = "(${getDataTypeName(it.dataType, it.modifier)}) ${it.name}" + (params == null ? "" : " " + params)  }
		
		return """- ${op.returnType == null ? '(void)' : '('+getDataTypeName(op.returnType, op.modifier) + ')'}${getOperationName(op)}${params == null ? '' : ':' + params}"""
	}
	
	/**
	 * Renders operation implementation. format '- [(returnType)|(void)] [operation name]([params]) { }'.
	 * @param op operation.
	 * @return operation implementation as a GString.
	 */
	def operationImplementation(IOperation op) {
		return """${operationSignature(op)} {
		}"""
	}
	
	/**
	 * Renders automatically '@class' imports based on dependent attributes. 
	 * @param c classifier.
	 * @return class imports as a GString.
	 */
	def classImports(IClassifier c) {
		def imports = ""
		c.attributes.findAll({!it.dataType.primitive}).each {
			imports += """@class ${it.dataType.name};\n"""
		}
		return imports
	}
}

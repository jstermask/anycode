package in.labulle.anycode.engine.groovy.directive

import in.labulle.anycode.uml.IAttribute
import in.labulle.anycode.uml.IClassifier

class ObjectiveCDirective {
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
	
	def attribute(IAttribute a) {
		return """${datatype(a)} ${getAttributeName(a)};"""
	}
	
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
	
	def datatype(IAttribute a, String collectionClassName) {
		if(a.getDataType().isPrimitive()) {
			return """${getDataTypeName(a.dataType, null)}"""
		} else {
			return """${!a.cardinality.single ? collectionClassName : getDataTypeName(a.dataType, a.modifier)}"""
		}
	}
	
	def datatype(IAttribute a) {
		return datatype(a, 'NSArray *')
	}
}

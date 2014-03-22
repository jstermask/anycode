package in.labulle.anycode.engine.groovy.directive

import in.labulle.anycode.uml.IAttribute
import in.labulle.anycode.uml.IClassifier

class ObjectiveCDirective {
	def attribute(IAttribute a) {
		
	}
	
	def getDataTypeName(IClassifier d) {
		def dtName = d.getName().toLowerCase()
		if(d.isPrimitive()) {
			if(dtName.indexOf("string") != -1) {
				return "NSString *"
			} else if(dtName.indexOf("integer") != -1) {
				return "NSInteger *"
			} else if(dtName.indexOf("float") != -1) {
				return "CGFloat *"
			} else if(dtName.indexOf("date") != -1) {
				return "NSDate *"
			} else if(dtName.indexOf("double") != -1) {
				return "double"
			} 
		}
		return d.getFullyQualifiedName(".")
	}
	
	def datatype(IAttribute a, String collectionClassName) {
		
	}
	
	def datatype(IAttribute a) {
		return datatype(a, 'NSArray *')
	}
}

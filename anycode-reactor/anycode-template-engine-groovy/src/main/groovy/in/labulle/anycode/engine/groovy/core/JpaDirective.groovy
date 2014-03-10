package in.labulle.anycode.engine.groovy.core



import java.lang.annotation.Annotation;

import in.labulle.anycode.uml.Cardinality;
import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.IOperation;
import in.labulle.anycode.uml.IRelationAttribute;
import in.labulle.anycode.uml.Visibility;
import in.labulle.anycode.uml.impl.AAttribute;
import in.labulle.anycode.uml.impl.AClass;
import in.labulle.anycode.uml.impl.ADataType;
import groovy.text.SimpleTemplateEngine

/**
 * JPA Directive.
 * @author Jose Carreno
 *
 */
class JpaDirective extends JavaDirective {
	/**
	 * @param a attribute to render
	 * @return attribute full signature as a script as well as JPA annotations : [visibility] [datatype] [attribute name]. e.g. : private String myVar;
	 */
	def static attribute(IAttribute a) {
		def javaAtt = JavaDirective.attribute(a)
		def script = """${annotation(a)}
			${javaAtt}"""
		return script;
	}

	/**
	 * Generates a primary key based on entity class. 3 possibilities :
	 * <ul>
	 * <li>Class has more than one "id" stereotyped attribute, then a PK class is rendered</li>
	 * <li>Class has exactly one "id" stereotyped attribute, then a JPA identifier will be generated based on this attribute.</li>
	 * <li>Attribute has no "id" stereotyped attribute, then a JPA Long identifier will be automatically generated, with sequence generation.</li>
	 * </ul> 
	 * @param c the class to render
	 * @return 
	 */
	def static primaryKey(IClass c) {
		def atts = c.attributes.findAll({it -> isIdentifier(it)})
		if(atts == null || atts.size() == 0) {
			return autoPrimaryKey(c)
		} else if(atts.size() > 1) {
			return compositePrimaryKey(c)
		} else {
			return singlePrimaryKey(atts[0], "@javax.persistence.Id")
		}
	}
	
	/**
	 * Generates a primary key datatype based on entity class. 3 possibilities :
	 * <ul>
	 * <li>Class has more than one "id" stereotyped attribute, then a PK class is rendered</li>
	 * <li>Class has exactly one "id" stereotyped attribute, then a JPA identifier will be generated based on this attribute.</li>
	 * <li>Attribute has no "id" stereotyped attribute, then a JPA Long identifier will be automatically generated, with sequence generation.</li>
	 * </ul>
	 * @param c the class to render
	 * @return
	 */
	def static primaryKeyDataType(IClass c) {
		def atts = c.attributes.findAll({it -> isIdentifier(it)})
		if(atts == null || atts.size() == 0) {
			return """java.lang.Long"""
		} else if(atts.size() > 1) {
			return """${c.getFullyQualifiedName(".")}PK"""
		} else {
			return """${getDataTypeName(atts[0].dataType)}"""
		}
	}

	def static annotation(IAttribute a) {
		if(!a.isRelation()) {
			return "";
		} else {
			def ar = (IRelationAttribute)a
			def anot = ""
			if(ar.isOneToOne()) {
				anot = "@javax.persistence.OneToOne"
			} else if (ar.isOneToMany()) {
				anot = "@javax.persistence.OneToMany"
			} else if(ar.isManyToMany()) {
				anot = "@javax.persistence.ManyToMany"
			} else if(ar.isManyToOne()) {
				anot = "@javax.persistence.ManyToOne"
			}
			return """${anot}${annotationOptions(a)}"""
		}
	}


	private def static annotationOptions(IAttribute a) {
		def options = null
		def mappedByOption = mappedBy(a)
		if(mappedByOption != null) {
			options = [mappedByOption]
		}
		def opts = null
		if(options != null) {
			options.reverseEach() { it -> opts = "${it}" + (opts == null ? "" : ", " + opts)  }
			return """($opts)"""
		} else {
			return ""
		}
	}

	private def static mappedBy(IAttribute a) {
		if(a instanceof IRelationAttribute) {
			IRelationAttribute ia = (IRelationAttribute)a
			if(!ia.isOwningSide() && ia.isBidirectionalRelation()) {
				return """mappedBy=\"${getAttributeName(ia.otherSide)}\""""
			}
		}
		return null;
	}


	def static isIdentifier(IAttribute a) {
		return a.hasStereotype("id")
	}

	def static isFinderOperation(IOperation op) {
		return op.name.startsWith("@")
	}

	private def static autoPrimaryKey(IClass c) {
		AAttribute aat = new AAttribute()
		aat.setName("id")
		aat.setVisibility(Visibility.PRIVATE)
		aat.setCardinality(Cardinality.ONE_TO_ONE)
		ADataType aatype = new ADataType()
		AClass cl = new AClass()
		cl.setName("java.lang.Long")
		aatype.setClassifier(cl)
		aat.setDataType(aatype)
		return singlePrimaryKey(aat, "@javax.persistence.Id")
	}


	private def static singlePrimaryKey(IAttribute a, String annotation) {
		def javaAtt = super.attribute(a)
		return """${annotation} ${javaAtt} 
			${getter(a)} 
			${setter(a)}"""
	}

	private def static compositePrimaryKey(IClass c) {
		AAttribute aat = new AAttribute()
		aat.setName("id")
		aat.setVisibility(Visibility.PRIVATE)
		aat.setCardinality(Cardinality.ONE_TO_ONE)
		ADataType aatype = new ADataType()
		AClass cl = new AClass()
		cl.setName(c.name + "PK")
		cl.setOwner(c.getOwner())
		aatype.setClassifier(cl)
		aat.setDataType(aatype)
		return singlePrimaryKey(aat, "@javax.persistence.EmbeddedId")
	}
}
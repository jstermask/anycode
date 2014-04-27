package in.labulle.anycode.engine.groovy.directive



import in.labulle.anycode.uml.Cardinality
import in.labulle.anycode.uml.IAttribute
import in.labulle.anycode.uml.IClass
import in.labulle.anycode.uml.IOperation
import in.labulle.anycode.uml.IRelationAttribute
import in.labulle.anycode.uml.Visibility
import in.labulle.anycode.uml.impl.AAttribute
import in.labulle.anycode.uml.impl.AClass

/**
 * JPA Directive. It extends {@link JavaDirective} and provides some useful method to generate JPA related code.
 * @author Jose Carreno
 *
 */
class JpaDirective extends JavaDirective {
	/**
	 * Render JPA Attribute with its annotation. See {@link #annotation(IAttribute)} for further details.
	 * @param a attribute to render.
	 * @return attribute full signature as a script as well as JPA annotations : [visibility] [datatype] [attribute name]. e.g. : private String myVar;.
	 */
	public def  attribute(IAttribute a) {
		def javaAtt = super.attribute(a)
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
	 * @param c the class to render.
	 * @return primary key declaration as a GString.
	 */
	public def  primaryKey(IClass c) {
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
	 * @param c the class to render.
	 * @return primary key's datatype as a GString.
	 */
	public def  primaryKeyDataType(IClass c) {
		def atts = c.attributes.findAll({it -> isIdentifier(it)})
		if(atts == null || atts.size() == 0) {
			return """java.lang.Long"""
		} else if(atts.size() > 1) {
			return """${c.getFullyQualifiedName(".")}PK"""
		} else {
			return """${getDataTypeName(atts[0].dataType, atts[0].modifier)}"""
		}
	}

	/**
	 * Renders attribute's annotations(@OneToOne, @OneToMany...). 
	 * @param a attribute.
	 * @return Annotations as a GString or "" if attribute is simple ({@link IAttribute#isRelation()} is false).
	 */
	public def  annotation(IAttribute a) {
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

	/**
	 * Render annotation default options if any. The current implementation take care of 'mappedBy' option when rendering bidirectional associations.
	 * @param a attribute.
	 * @return annotation JPA options as a GString or en empty String if there are no options to render.
	 */
	private def  annotationOptions(IAttribute a) {
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

	/**
	 * Renders mappedBy annotation option on bidirectional associations.
	 * @param a attribute.
	 * @return mappedBy JPA option as a GString.
	 */
	private def  mappedBy(IAttribute a) {
		if(a instanceof IRelationAttribute) {
			IRelationAttribute ia = (IRelationAttribute)a
			if(!ia.isOwningSide() && ia.isBidirectionalRelation()) {
				return """mappedBy=\"${getAttributeName(ia.otherSide)}\""""
			}
		}
		return null;
	}


	/**
	 * Checks whether the given attribute is identifier, i.e. : whether it has an stereotype named 'id'
	 * @param a attribute.
	 * @return true is this attribute is part of the primary key of the entity, false otherwise.
	 */
	public def  isIdentifier(IAttribute a) {
		return a.hasStereotype("id")
	}

	/**
	 * Checks whether the given operation is a finder operation, i.e. : whether it begins with '@' prefix. 
	 * The purpose of this method is to filter classifier operations and generate them in data access objects (DAO or repositories using Spring Data). This mechanism 
	 * is borrowed from <a href="http://www.andromda.org/andromda-cartridges/andromda-spring-cartridge/howto6.html" target="blank">AndroMDA</a> spring cartridge conventions.
	 * @param op operation
	 * @return true if operation starts with '@', false otherwise.
	 */
	public def  isFinderOperation(IOperation op) {
		return op.name.startsWith("@")
	}

	/**
	 * Calculate operation's name. This is used to render the proper operation name even it is a finder method ({@link #isFinderOperation(IOperation)}).
	 * @param op opearation
	 * @return operation's name. 
	 */
	protected def getOperationName(IOperation op) {
		if(isFinderOperation(op)) {
			return op.getName().substring(1)
		} else {
			return super.getOperationName(op)
		}
	}

	/**
	 * Renders an auto generated primary key. This is used in case there is no primary key identified by JPA Directive. The key is a {@link Long} named 'id' with its getter and setter. This method calls {@link #singlePrimaryKey(IAttribute, String)}.
	 * @param c class.
	 * @return primary key, its getter and its setter as a GString
	 */
	private def  autoPrimaryKey(IClass c) {
		AAttribute aat = new AAttribute()
		aat.setName("id")
		aat.setVisibility(Visibility.PRIVATE)
		aat.setCardinality(Cardinality.ONE_TO_ONE)
		AClass cl = new AClass()
		cl.setName("java.lang.Long")
		aat.setDataType(cl)
		return singlePrimaryKey(aat, "@javax.persistence.Id")
	}


	/**
	 * Generates a primary key with the given attribute and annotation.
	 * @param a attribute to render.
	 * @param annotation JPA identifier annotation (e.g. : '@javax.persistence.Id' or '@EmbeddedId' depending the caller's requirements.)
	 * @return primary key, its getter and its setter as a GString
	 */
	private def  singlePrimaryKey(IAttribute a, String annotation) {
		def javaAtt = super.attribute(a)
		return """${annotation} ${javaAtt} 
			${getter(a)} 
			${setter(a)}"""
	}

	/**
	 * Generates a composite primary key based on the given class. The result a the declaration of a key with a specific class [classname]PK (e.g. : MyClass => MyClassPK). 
	 * This PK class will also have to be generated with a specific anycode template. 
	 * @param c class
	 * @return composite primary key, its getter and its setter as a GString
	 */
	private def  compositePrimaryKey(IClass c) {
		AAttribute aat = new AAttribute()
		aat.setName("id")
		aat.setVisibility(Visibility.PRIVATE)
		aat.setCardinality(Cardinality.ONE_TO_ONE)

		AClass cl = new AClass()
		cl.setName(c.name + "PK")
		cl.setOwner(c.getOwner())
		aat.setDataType(cl)
		return singlePrimaryKey(aat, "@javax.persistence.EmbeddedId")
	}
}
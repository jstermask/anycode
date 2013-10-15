package in.labulle.anycode.xmi;

import in.labulle.anycode.api.UMLAssociation;
import in.labulle.anycode.api.UMLAttribute;
import in.labulle.anycode.api.UMLClass;
import in.labulle.anycode.api.UMLDatatype;
import in.labulle.anycode.api.UMLElement;
import in.labulle.anycode.api.UMLInterface;
import in.labulle.anycode.api.UMLModel;
import in.labulle.anycode.api.UMLMultiplicity;
import in.labulle.anycode.api.UMLMultiplicityRange;
import in.labulle.anycode.api.UMLPackage;
import in.labulle.anycode.api.UMLStereotype;
import in.labulle.anycode.xmi.mapper.UMLElementMapper;

public enum XmiTag {
	CLASS("Class", UMLClass.class, new UMLElementMapper()), PACKAGE("Package",
			UMLPackage.class, new UMLElementMapper()), ATTRIBUTE("Attribute",
			UMLAttribute.class, new UMLElementMapper()), DATATYPE("Datatype",
			UMLDatatype.class, new UMLElementMapper()), ASSOCIATION(
			"Association", UMLAssociation.class, new UMLElementMapper()), MODEL(
			"Model", UMLModel.class, new UMLElementMapper()), STEREOTYPE(
			"Stereotype", UMLStereotype.class, new UMLElementMapper()), MULTIPLICITY(
			"Multiplicity", UMLMultiplicity.class, new UMLElementMapper()), MULTIPLICITY_RANGE(
			"MultiplicityRange", UMLMultiplicityRange.class,
			new UMLElementMapper()), INTERFACE("Interface", UMLInterface.class,
			new UMLElementMapper());

	/**
	 * Tag representation as a String.
	 */
	private final String tag;

	private final UMLElementMapper mapperInstance;

	private final Class<?> umlClass;

	/**
	 * Private constructor
	 * 
	 * @param tag
	 *            string representation
	 */
	private XmiTag(final String tag, final Class<?> umlClass,
			final UMLElementMapper mapperInstance) {
		this.tag = tag;
		this.umlClass = umlClass;
		this.mapperInstance = mapperInstance;
	}

	/**
	 * 
	 * @return string representation
	 */
	public final String getTag() {
		return this.tag;
	}

	public UMLElementMapper getMapperInstance() {
		return mapperInstance;
	}

	public Class<?> getUmlClass() {
		return umlClass;
	}

	public static XmiTag fromString(final String tagName) {
		for (XmiTag tag : XmiTag.values()) {
			if (tag.getTag().equals(tagName)) {
				return tag;
			}
		}
		return null;
	}

	public UMLElement getNewUMLInstance() {
		try {
			return (UMLElement) getUmlClass().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

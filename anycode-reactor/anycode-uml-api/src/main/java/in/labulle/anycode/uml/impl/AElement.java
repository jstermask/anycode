package in.labulle.anycode.uml.impl;

import java.util.HashSet;
import java.util.Set;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IElement;
import in.labulle.anycode.uml.IPackage;
import in.labulle.anycode.uml.IStereotype;
import in.labulle.anycode.uml.Visibility;

public class AElement implements IElement {

	private String name;

	private IElement owner;

	private Visibility visibility;

	private String modifier;
	
	private String documentation;

	private final Set<IStereotype> stereotypes = new HashSet<IStereotype>();

	public AElement() {
		super();
	}
	
	public AElement(String name) {
		this();
		setName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IElement getOwner() {
		return owner;
	}

	public void setOwner(IElement owner) {
		this.owner = owner;
		if(this instanceof IPackage) {
			((IPackage)owner).addSubPackage((IPackage)this);
		} else if (this instanceof IClassifier) {
			((IPackage)owner).addClassifier((IClassifier)this);
		}
	}

	public Set<IStereotype> getStereotypes() {
		return stereotypes;
	}

	public void addStereotype(final IStereotype stereotype) {
		this.stereotypes.add(stereotype);
	}

	public boolean hasStereotype(final String stereotype) {
		return this.stereotypes.contains(new AStereotype(stereotype));
	}

	public String getFullyQualifiedName(String separator) {
		StringBuffer buf = new StringBuffer(getName());
		IElement owner = getOwner();
		while (owner != null) {
			buf.insert(0, owner.getName() + separator);
			owner = owner.getOwner();
		}
		return buf.toString();
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	public String getDocumentation() {
		return documentation;
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
}

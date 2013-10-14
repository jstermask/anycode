package in.labulle.anycode.api;

import java.util.ArrayList;
import java.util.List;

public abstract class UMLElement {
	private String name;
	private UMLElement owner;
	private List<String> stereotypes = new ArrayList<String>();
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof UMLElement && getId() != null) {
			UMLElement item = (UMLElement) obj;
			return getId().equals(item.getId());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : super.hashCode();
	}

	public String getName() {
		return name;
	}

	public UMLElement getOwner() {
		return owner;
	}

	public List<String> getStereotypes() {
		return stereotypes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(UMLElement newOwner) {
		if (this.owner != null && this.owner instanceof UMLPackage) {
			UMLPackage pack = (UMLPackage) this.owner;
			if (!pack.getOwnedElements().contains(this)) {
				pack.removeElement(this);
			}
		}
		this.owner = newOwner;
		if (this.owner != null && this.owner instanceof UMLPackage) {
			UMLPackage pack = (UMLPackage) this.owner;
			if (!pack.getOwnedElements().contains(this)) {
				pack.addOwnedElement(this);
			}
		}
	}

	public void setStereotypes(List<String> stereotypes) {
		this.stereotypes = stereotypes;
	}
	
	public String getFullNamespace(String separator) {
		StringBuffer w = new StringBuffer();
		UMLElement o = this.owner;
		while(o != null) {
			if((o instanceof UMLPackage) && !(o instanceof UMLModel))  {
				if(w.length() != 0) {
					w.insert(0,separator);
				}
				w.insert(0, o.getName());
			}
			o = o.getOwner();
		}
		return w.toString();
	}

}

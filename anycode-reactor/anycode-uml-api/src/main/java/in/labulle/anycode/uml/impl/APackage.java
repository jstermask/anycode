package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IPackage;

import java.util.ArrayList;
import java.util.List;

public class APackage extends AElement implements IPackage {
	private final List<IPackage> subPackages = new ArrayList<IPackage>();

	private final List<IClassifier> classifiers = new ArrayList<IClassifier>();

	private final List<String> constraints = new ArrayList<String>();

	public APackage() {
		super();
	}

	public APackage(String name) {
		super(name);
	}

	public List<IPackage> getSubPackages() {
		return new ArrayList<IPackage>(subPackages);
	}

	public List<IClassifier> getClassifiers() {
		return new ArrayList<IClassifier>(classifiers);
	}

	public void addSubPackage(IPackage pack) {
		if (!this.subPackages.contains(pack)) {
			this.subPackages.add(pack);
		}
	}

	public void addClassifier(IClassifier cl) {
		if (!this.classifiers.contains(cl)) {
			this.classifiers.add(cl);
		}
	}


	public IClassifier findClassifierByFullyQualifiedName(String fullyQualifiedName, String separator) {
		if(fullyQualifiedName == null || "".equals(fullyQualifiedName)) {
			return null;
		} else {	
			for(IClassifier cl : classifiers) {
				if(fullyQualifiedName.equalsIgnoreCase(cl.getFullyQualifiedName(separator))) {
					return cl;
				}
			}
			for(IPackage pk : subPackages) {
				IClassifier cl = pk.findClassifierByFullyQualifiedName(fullyQualifiedName, separator);
				if(cl != null) {
					return cl;
				}
			}
		}
		return null;
	}

	public List<String> getConstraints() {
		return constraints;
	}

	public void addConstraint(final String c) {
		this.constraints.add(c);
	}
	public void removeConstraint(final String c) {
		this.constraints.remove(c);
	}
}

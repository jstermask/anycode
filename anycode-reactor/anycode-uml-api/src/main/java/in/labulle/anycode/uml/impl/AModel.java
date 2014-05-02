package in.labulle.anycode.uml.impl;

import java.util.ArrayList;
import java.util.List;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.uml.IPackage;

public class AModel implements IModel {

	private final List<IPackage> packages = new ArrayList<IPackage>();

	private final List<IClassifier> classifiers = new ArrayList<IClassifier>();

	public List<IClassifier> getAllClasses() {
		List<IClassifier> cls = new ArrayList<IClassifier>();
		for (IPackage p : getPackages()) {
			addClassesRecursively(p, cls);
		}
		return cls;
	}

	public List<IPackage> getPackages() {
		return packages;
	}

	public List<IClassifier> getClassifiers() {
		return classifiers;
	}

	private void addClassesRecursively(IPackage p, List<IClassifier> cls) {
		cls.addAll(p.getClassifiers());
		for (IPackage sp : p.getSubPackages()) {
			addClassesRecursively(sp, cls);
		}
	}

	
	public IClassifier findClassifierByFullyQualifiedName(final String fullyQualifiedName, final String separator) {
		if(fullyQualifiedName == null || "".equals(fullyQualifiedName)) {
			return null;
		} else {	
			for(IClassifier cl : classifiers) {
				if(fullyQualifiedName.equalsIgnoreCase(cl.getFullyQualifiedName(separator))) {
					return cl;
				}
			}
			for(IPackage pk : packages) {
				IClassifier cl = pk.findClassifierByFullyQualifiedName(fullyQualifiedName, separator);
				if(cl != null) {
					return cl;
				}
			}
		}
		return null;
	}

}

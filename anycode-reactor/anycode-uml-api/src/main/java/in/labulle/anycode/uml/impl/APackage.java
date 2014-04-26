package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IPackage;

import java.util.ArrayList;
import java.util.List;

public class APackage extends AElement implements IPackage {
	private final List<IPackage> subPackages = new ArrayList<IPackage>();

	private final List<IClassifier> classifiers = new ArrayList<IClassifier>();

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
		this.subPackages.add(pack);
	}

	public void addClassifier(IClassifier cl) {
		this.classifiers.add(cl);
	}

}

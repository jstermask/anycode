package in.labulle.anycode.uml;

import java.util.List;

public interface IPackage extends IElement {
	List<IPackage> getSubPackages();
	void addSubPackage(IPackage pack);
	List<IClassifier> getClassifiers();
	void addClassifier(IClassifier cl);
}

package in.labulle.anycode.api;

public class UMLModel extends UMLPackage {
	@Override
	public void setOwner(UMLElement newOwner) {
		if (newOwner != null) {
			throw new UnsupportedOperationException(getClass().getName()
					+ " object cannot be owned by an instance of class "
					+ newOwner.getClass().getName());
		}
	}
}

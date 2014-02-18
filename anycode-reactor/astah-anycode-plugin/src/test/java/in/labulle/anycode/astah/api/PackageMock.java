package in.labulle.anycode.astah.api;

import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.model.IConstraint;
import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.model.IElement;
import com.change_vision.jude.api.inf.model.IHyperlink;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.model.IRealization;
import com.change_vision.jude.api.inf.model.IUsage;

public class PackageMock extends ElementMock implements IPackage {

	@Override
	public IHyperlink createElementHyperlink(IElement arg0, String arg1)
			throws InvalidEditingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IHyperlink createFileHyperlink(String arg0, String arg1, String arg2)
			throws InvalidEditingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IHyperlink createURLHyperlink(String arg0, String arg1)
			throws InvalidEditingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteHyperlink(IHyperlink arg0) throws InvalidEditingException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getAlias1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlias2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDependency[] getClientDependencies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRealization[] getClientRealizations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IUsage[] getClientUsages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IConstraint[] getConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDiagram[] getDiagrams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFullName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFullNamespace(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IHyperlink[] getHyperlinks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public INamedElement[] getOwnedElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDependency[] getSupplierDependencies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRealization[] getSupplierRealizations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IUsage[] getSupplierUsages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPackageVisibility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPrivateVisibility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isProtectedVisibility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPublicVisibility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAlias1(String arg0) throws InvalidEditingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAlias2(String arg0) throws InvalidEditingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDefinition(String arg0) throws InvalidEditingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setVisibility(String arg0) throws InvalidEditingException {
		// TODO Auto-generated method stub

	}


}

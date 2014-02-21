package in.labulle.anycode.astah.api;

import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.exception.InvalidUsingException;
import com.change_vision.jude.api.inf.model.IComment;
import com.change_vision.jude.api.inf.model.IElement;
import com.change_vision.jude.api.inf.model.ITaggedValue;
import com.change_vision.jude.api.inf.presentation.IPresentation;

public class ElementMock implements IElement {
	private String name;

	@Override
	public void addStereotype(String arg0) throws InvalidEditingException {
		// TODO Auto-generated method stub

	}

	@Override
	public IComment[] getComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IElement getContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IElement[] getContainers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return getName();
	}

	public String getName() {
		return name;
	}

	@Override
	public IElement getOwner() {
		return null;
	}

	@Override
	public IPresentation[] getPresentations() throws InvalidUsingException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String[] getStereotypes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getTaggedValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITaggedValue[] getTaggedValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTypeModifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasStereotype(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReadOnly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeStereotype(String arg0) throws InvalidEditingException {
		// TODO Auto-generated method stub

	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public void setTypeModifier(String arg0) throws InvalidEditingException {
		// TODO Auto-generated method stub

	}

}

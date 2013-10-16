package in.labulle.anycode.astah.plugin.model.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.IModel;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;

public final class ModelUtils {
	/**
	 * log
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ModelUtils.class);

	public static final List<IClass> getAllClasses(IModel model) {
		List<IClass> classeList = new ArrayList<IClass>();
		getAllClasses(model, classeList);
		return classeList;
	}

	/**
	 * Adds recursively classes found from the given named element.
	 * 
	 * @param element
	 *            any uml element (cannot be null).
	 * @param classList
	 *            list (can be empty but not null)
	 */
	private static final void getAllClasses(INamedElement element,
			List<IClass> classList) {
		if (element instanceof IPackage) {
			for (INamedElement ownedNamedElement : ((IPackage) element)
					.getOwnedElements()) {
				getAllClasses(ownedNamedElement, classList);
			}
		} else if (element instanceof IClass) {
			if (!isJREClass((IClass) element)) {
				classList.add((IClass) element);
				if (LOG.isDebugEnabled()) {
					LOG.debug("Class " + element.getFullName(".") + " found...");
				}
				for (IClass nestedClasses : ((IClass) element)
						.getNestedClasses()) {
					getAllClasses(nestedClasses, classList);
				}
			}
		}
	}
	
	public static boolean isJREClass(IClass classe) {
		return classe.getFullName(".").indexOf("java") == 0
				|| classe.getName().length() == 1;
	}
}

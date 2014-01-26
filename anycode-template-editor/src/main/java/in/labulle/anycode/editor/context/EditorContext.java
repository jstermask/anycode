package in.labulle.anycode.editor.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EditorContext implements IEditorContext {
	private final List<IDirectiveContext> directiveContexts = new ArrayList<>();

	private int activeDirectiveContextIndex;

	@Override
	public List<IDirectiveContext> getOpenDirectiveContexts() {
		return Collections.unmodifiableList(directiveContexts);
	}

	@Override
	public void close() {
		directiveContexts.clear();
	}

	public IDirectiveContext newDirectiveContext() {
		DirectiveContext d = new DirectiveContext(this);
		this.directiveContexts.add(d);
		setActiveDirective(d);
		return d;
	}

	@Override
	public void removeDirectiveContext(IDirectiveContext ctx) {
		this.directiveContexts.remove(ctx);
	}

	@Override
	public IDirectiveContext getActiveDirectiveContext() {
		return directiveContexts.get(activeDirectiveContextIndex);
	}

	@Override
	public void setActiveDirectiveContext(int index) {
		if (index >= 0 && index < directiveContexts.size()) {
			this.activeDirectiveContextIndex = index;
		}
	}
	
	private void setActiveDirective(IDirectiveContext c) {
		this.activeDirectiveContextIndex = directiveContexts.indexOf(c);
	}
}

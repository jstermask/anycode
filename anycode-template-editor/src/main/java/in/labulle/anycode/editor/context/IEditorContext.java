package in.labulle.anycode.editor.context;


public interface IEditorContext extends IContext {
	java.util.List<IDirectiveContext> getOpenDirectiveContexts();
	IDirectiveContext newDirectiveContext();
	void removeDirectiveContext(IDirectiveContext ctx);
	IDirectiveContext getActiveDirectiveContext();
	void setActiveDirectiveContext(int ctx);
}

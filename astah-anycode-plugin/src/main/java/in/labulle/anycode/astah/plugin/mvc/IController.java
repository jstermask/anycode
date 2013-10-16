package in.labulle.anycode.astah.plugin.mvc;

public interface IController<T extends IContext> {
	T getContext();
	void register(T context);
}

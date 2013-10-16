package in.labulle.anycode.astah.plugin.mvc;



public interface IView<T extends IController> {
	T getController();
	void register(T controller);
}

package in.labulle.anycode.astah.plugin.generator.ui.utils;

import java.awt.Container;
import java.awt.Window;

import in.labulle.anycode.astah.plugin.generator.ui.tab.CodeConfigurationTabView;

public class UIUtils {
	public static final CodeConfigurationTabView getView(Container c) {
		Container parent = c;
		while(parent != null && !(parent instanceof CodeConfigurationTabView)) {
			parent = parent.getParent();
		}
		return (CodeConfigurationTabView)parent;
	}
	
	public static final Window getWindow(Container c) {
		Container parent = c;
		while(parent != null && !(parent instanceof Window)) {
			parent = parent.getParent();
		}
		return (Window)parent;
	}
}

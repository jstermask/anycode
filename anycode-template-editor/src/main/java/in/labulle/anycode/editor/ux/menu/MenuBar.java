package in.labulle.anycode.editor.ux.menu;

import in.labulle.anycode.editor.controller.IDirectiveFileController;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	/**
	 * File controller
	 */
	private final IDirectiveFileController directiveFileController;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2436255515398532506L;
	
	public MenuBar(final IDirectiveFileController directiveFileController) {
		this.directiveFileController = directiveFileController;
		init();
	}
	
	private void init() {
		JMenu menu = new JMenu("File");
		menu.add(new JMenuItem("Open..."));
		menu.add(new JMenuItem("Save"));
		menu.add(new JMenuItem("Quit"));
		add(menu);
	}

}

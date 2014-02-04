package in.labulle.anycode.editor.ux.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import in.labulle.anycode.editor.service.IDirectiveFacade;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	/**
	 * File controller
	 */
	private final IDirectiveFacade directiveFileController;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2436255515398532506L;

	public MenuBar(final IDirectiveFacade directiveFileController) {
		this.directiveFileController = directiveFileController;
		init();
	}

	private void init() {
		JMenu menu = new JMenu("File");
		menu.add(getFileOpenMenuItem());
		menu.add(new JMenuItem("Save"));
		menu.add(new JMenuItem("Quit"));
		add(menu);
	}

	private JMenuItem getFileOpenMenuItem() {
		final JMenuItem item = new JMenuItem("Open...");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(item);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					directiveFileController.loadFromSelectedFile(f);
				}
			}
		});
		return item;
	}

}

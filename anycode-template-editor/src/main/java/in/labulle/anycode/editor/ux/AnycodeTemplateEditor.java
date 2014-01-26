package in.labulle.anycode.editor.ux;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class AnycodeTemplateEditor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7036826508150802814L;

	public static void main(String[] args) {
		JFrame frame = new AnycodeTemplateEditor();
		frame.setContentPane(getMainPanel());
		frame.setJMenuBar(newMenuBar());
		frame.pack();
		frame.setVisible(true);

	}

	private static JMenuBar newMenuBar() {
		JMenuBar mb = new JMenuBar();
		mb.add(newMenu());
		return mb;
	}

	private static JMenu newMenu() {
		JMenu menu = new JMenu("File");
		menu.add(new JMenuItem("Open..."));
		menu.add(new JMenuItem("Save"));
		menu.add(new JMenuItem("Quit"));
		return menu;
	}

	private static JPanel getMainPanel() {
		return new JPanel();
	}

}

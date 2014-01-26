package in.labulle.anycode.editor.ux;

import in.labulle.anycode.editor.context.EditorContext;
import in.labulle.anycode.editor.context.IEditorContext;
import in.labulle.anycode.editor.controller.DirectiveFileController;
import in.labulle.anycode.editor.ux.menu.MenuBar;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class AnycodeTemplateEditor extends JFrame {
	private final IEditorContext editorContext;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7036826508150802814L;

	public static void main(String[] args) {
		JFrame frame = new AnycodeTemplateEditor(new EditorContext());
		frame.pack();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);

	}

	public AnycodeTemplateEditor(final IEditorContext ctx) {
		this.editorContext = ctx;
		build();
	}
	
	private void build() {
		setJMenuBar(newMenuBar());
		setContentPane(newMainPanel());
	}
	
	private JMenuBar newMenuBar() {
		//MenuBar bar = new MenuBar(new DirectiveFileController(this.editorContext));
		//return bar;
		return null;
	}
	

	private JPanel newMainPanel() {
		return new JPanel();
	}

}

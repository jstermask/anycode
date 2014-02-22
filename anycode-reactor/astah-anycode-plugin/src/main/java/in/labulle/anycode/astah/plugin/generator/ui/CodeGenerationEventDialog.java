package in.labulle.anycode.astah.plugin.generator.ui;

import in.labulle.anycode.engine.log.ICodeGenerationEvent;
import in.labulle.anycode.engine.osgi.BundleUtils;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class CodeGenerationEventDialog extends JDialog {
	private ICodeGenerationEvent event;

	private final JTextArea failure = new JTextArea();

	/**
	 * 
	 */
	private static final long serialVersionUID = -6341586989509018298L;

	public CodeGenerationEventDialog(Window parent) {
		super(parent);
		setTitle(BundleUtils.getMessage("title_event"));
		setModal(true);
		setResizable(true);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		initComponents();
		pack();
		setLocationRelativeTo(parent);

	}

	private void initComponents() {
		setContentPane(getPanel());
		setPreferredSize(new Dimension(900, 500));
		applyStyle(failure);
	}

	private JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		JScrollPane p = new JScrollPane(failure);
		p.setAutoscrolls(true);
		panel.add(p);
		return panel;
	}

	public void setEvent(ICodeGenerationEvent event) {
		this.event = event;
		if (event != null) {
			this.failure.setText(""+event);
			this.failure.append("\n");
			this.failure.append(event.getDetails());
			this.failure.append("\n\n");
			switch (this.event.getStatus()) {
			case FAILURE:
				updateFailureText(this.event.getFailureCause());
				break;
			case SUCCESS:
				updateSuccessText(this.event.getGeneratedFile());
				break;
			default:
				break;
			}
		} else {
			this.failure.setText(BundleUtils.getMessage("msg_no_event"));
		}
		repaint();
	}

	private void updateSuccessText(final File f) {
		BufferedReader fr = null;
		try {
			if (f != null) {
				fr = new BufferedReader(new FileReader(f));
				while (fr.ready()) {
					this.failure.append(fr.readLine());
					this.failure.append("\n");
				}
			}
		} catch (IOException e) {

		} finally {
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					
				}
			}
		}
	}

	private void updateFailureText(final Throwable error) {
		if (error != null) {
			Throwable e = error;
			StringBuffer buf = new StringBuffer("");
			int its = 0;
			String prefix = "";
			String cause = "";
			do {
				buf.append(prefix).append(cause).append(e.getMessage());
				for (StackTraceElement stk : e.getStackTrace()) {
					buf.append("\n").append(prefix).append(stk.toString());
				}
				e = e.getCause();
				prefix = "\t" + prefix;
				cause = "\nCaused by : ";
				its++;
			} while (e != null && its < 5);
			this.failure.append(buf.toString());
		}
	}

	private void applyStyle(JTextArea area) {
		area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
		area.setLineWrap(true);
		area.setEditable(false);
	}

}

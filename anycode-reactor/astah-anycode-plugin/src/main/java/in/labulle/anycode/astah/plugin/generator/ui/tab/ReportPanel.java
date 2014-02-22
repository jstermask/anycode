package in.labulle.anycode.astah.plugin.generator.ui.tab;

import in.labulle.anycode.astah.plugin.generator.ui.CodeGenerationEventDialog;
import in.labulle.anycode.astah.plugin.generator.ui.CodeGenerationLogTableModel;
import in.labulle.anycode.astah.plugin.generator.ui.utils.UIUtils;
import in.labulle.anycode.engine.log.ICodeGenerationEvent;
import in.labulle.anycode.engine.log.ICodeGenerationLog;
import in.labulle.anycode.engine.osgi.BundleUtils;
import in.labulle.anycode.engine.osgi.ImageBundle;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

public class ReportPanel extends JPanel implements Observer {
	private CodeGenerationLogTableModel codeGenerationLogTableModel;

	private JProgressBar progressBar;

	private JTable report;
	/**
	 * Event Dialog
	 */
	private CodeGenerationEventDialog codeGenerationEventDialog;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5266101422492926985L;

	public ReportPanel() {
		initComponents();
	}

	private void initComponents() {
		this.codeGenerationEventDialog = new CodeGenerationEventDialog(
				UIUtils.getWindow(this));
		setBorder(new TitledBorder(
				BundleUtils.getMessage("fieldset_code_generation_report")));
		BoxLayout bl = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(bl);
		add(buildTopBar());
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(buildReportTable());
	}

	private JPanel buildTopBar() {
		JPanel topBar = new JPanel();
		topBar.setLayout(new GridLayout(2, 1));
		topBar.add(buildIconBar());
		topBar.add(buildProgressBar());
		return topBar;
	}

	private JPanel buildIconBar() {
		JPanel bar = new JPanel();
		return bar;
	}

	private JProgressBar buildProgressBar() {
		this.progressBar = new JProgressBar(0, 100);
		progressBar.setStringPainted(true);
		return progressBar;
	}

	private JPanel buildReportTable() {
		this.codeGenerationLogTableModel = new CodeGenerationLogTableModel();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		this.report = new JTable(codeGenerationLogTableModel);
		this.report.getColumnModel().getColumn(0).setPreferredWidth(100);
		this.report.getColumnModel().getColumn(1).setPreferredWidth(300);
		this.report.getColumnModel().getColumn(2).setPreferredWidth(500);
		this.report.getColumnModel().getColumn(3).setPreferredWidth(1000);
		
		this.report.addMouseListener(getTableListener(this));
		JScrollPane scrollPane = new JScrollPane(report);
		report.setFillsViewportHeight(true);
		panel.add(scrollPane);
		codeGenerationLogTableModel.addObserver(this);
		return panel;
	}

	public CodeGenerationLogTableModel getCodeGenerationLogTableModel() {
		return codeGenerationLogTableModel;
	}

	@Override
	public void update(Observable o, Object arg) {
		ICodeGenerationLog log = (ICodeGenerationLog) o;
		this.progressBar.setValue(log.getProgress());
		this.progressBar.setString(log.getProgress() + "%");
		this.report.repaint();
	}

	private MouseListener getTableListener(final Container c) {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				showMenu(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				showMenu(e);
			}

			private void showMenu(MouseEvent e) {
				if (e.isPopupTrigger()) {
					JTable target = (JTable) e.getSource();
					int row = target.rowAtPoint( e.getPoint() );
					if (row >= 0) {
						target.changeSelection( row, 0, false, false );
						CodeGenerationLogTableModel model = ((CodeGenerationLogTableModel) target
								.getModel());
						ICodeGenerationEvent event = model.getContent()
								.get(row);
						showEventMenu(e, event);
					}
				}
			}
		};
	}

	public void showEventMenu(final MouseEvent evt,
			final ICodeGenerationEvent event) {
		JPopupMenu menu = new JPopupMenu();
		JMenuItem item = new JMenuItem(
				BundleUtils.getMessage("menu_item_show_details"),
				new ImageIcon(ImageBundle.getBundle()
						.getImageURL("details.gif")));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				codeGenerationEventDialog.setEvent(event);
				codeGenerationEventDialog.setVisible(true);
			}
		});
		menu.add(item);
		menu.show(evt.getComponent(), evt.getX(), evt.getY());
	}

}

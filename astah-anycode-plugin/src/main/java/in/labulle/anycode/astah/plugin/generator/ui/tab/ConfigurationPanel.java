package in.labulle.anycode.astah.plugin.generator.ui.tab;

import in.labulle.anycode.astah.plugin.generator.control.CodeGenerationController;
import in.labulle.anycode.astah.plugin.mvc.IView;
import in.labulle.anycode.astah.plugin.osgi.util.BundleUtils;
import in.labulle.anycode.astah.plugin.osgi.util.ImageBundle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ConfigurationPanel extends JPanel {

	private final JButton buttonExportDirectoryBrowse = new JButton(
			BundleUtils.getMessage("button_browse"));

	private final JButton buttonTemplateDirectoryBrowse = new JButton(
			BundleUtils.getMessage("button_browse"));

	private final JFileChooser fileChooser = new JFileChooser();

	private final JTextField textExportDirectory = new JTextField(40);

	private final JTextField textTemplateDirectory = new JTextField(40);

	private JLabel labelMacrosCount = new JLabel(new ImageIcon(ImageBundle
			.getBundle().getImageURL("built-in-directives.gif")), JLabel.HORIZONTAL);

	private JLabel labelTemplatesCount = new JLabel(new ImageIcon(ImageBundle
			.getBundle().getImageURL("user-templates.gif")), JLabel.HORIZONTAL);

	private final JButton buttonGenerate = new JButton(
			BundleUtils.getMessage("button_generate"), new ImageIcon(ImageBundle.getBundle().getImageURL("generate.gif")));

	private final JButton buttonClearResults = new JButton(
			BundleUtils.getMessage("button_clear_results"),new ImageIcon(ImageBundle.getBundle().getImageURL("clear.gif")));

	/**
	 * 
	 */
	private static final long serialVersionUID = 9034712023401970663L;

	public ConfigurationPanel() {
		initComponents();
		setMinimumSize(new Dimension(300, 150));
		setPreferredSize(new Dimension(400, 200));
	}

	private void initComponents() {
		setBorder(new TitledBorder(BundleUtils.getMessage("fieldset_run")));
		setLayout(new BorderLayout());
		initGenerateButton();
		initClearButton();
		initChampRepertoireExport();
		initChampRepertoireTemplate();
		initBoutonParcourirRepertoireExport();
		initBoutonParcourirRepertoireTemplate();
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		add(buildConfigurationForm(), BorderLayout.CENTER);
		add(buildActionBar(), BorderLayout.SOUTH);
	}

	private JPanel buildConfigurationForm() {
		JLabel labelExport = new JLabel(
				BundleUtils.getMessage("label_output_dir"));
		JLabel labelTemplates = new JLabel(
				BundleUtils.getMessage("label_template_dir"));
		JPanel fields = new JPanel();
		GroupLayout groupLayout = new GroupLayout(fields);
		fields.setLayout(groupLayout);
		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.setAutoCreateGaps(true);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createSequentialGroup()
						.addGroup(
								groupLayout
										.createParallelGroup(
												GroupLayout.Alignment.LEADING)
										.addComponent(labelTemplates)
										.addComponent(labelExport))
						.addGroup(
								groupLayout
										.createParallelGroup(
												GroupLayout.Alignment.TRAILING)
										.addComponent(
												this.textTemplateDirectory)
										.addComponent(this.textExportDirectory))
						.addGroup(
								groupLayout
										.createParallelGroup(
												GroupLayout.Alignment.TRAILING)
										.addComponent(
												this.buttonTemplateDirectoryBrowse)
										.addComponent(
												this.buttonExportDirectoryBrowse)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createSequentialGroup()
						.addGroup(
								groupLayout
										.createParallelGroup(
												GroupLayout.Alignment.LEADING)
										.addComponent(labelTemplates)
										.addComponent(
												this.textTemplateDirectory)
										.addComponent(
												this.buttonTemplateDirectoryBrowse))
						.addGroup(
								groupLayout
										.createParallelGroup(
												GroupLayout.Alignment.LEADING)
										.addComponent(labelExport)
										.addComponent(this.textExportDirectory)
										.addComponent(
												this.buttonExportDirectoryBrowse)));
		return fields;
	}

	private JPanel buildActionBar() {
		JPanel bar = new JPanel();
		bar.setLayout(new BorderLayout());
		// buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		buttons.add(this.buttonGenerate);
		buttons.add(this.buttonClearResults);
		buttons.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		bar.add(buttons, BorderLayout.EAST);

		// macros infos
		setTemplatesAndMacrosCount(0, 0);
		JPanel templatesAndMacrosInfos = new JPanel();
		BoxLayout bl = new BoxLayout(templatesAndMacrosInfos,
				BoxLayout.PAGE_AXIS);
		templatesAndMacrosInfos.setLayout(bl);
		templatesAndMacrosInfos.setAlignmentY(0f);
		templatesAndMacrosInfos.add(this.labelTemplatesCount);
		templatesAndMacrosInfos.add(Box.createRigidArea(new Dimension(0, 3)));
		templatesAndMacrosInfos.add(this.labelMacrosCount);
		bar.add(templatesAndMacrosInfos, BorderLayout.CENTER);
		return bar;
	}

	protected void initGenerateButton() {
		this.buttonGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getController().generateCode();
			}
		});
	}

	protected void initClearButton() {
		this.buttonClearResults.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getController().reset();
			}
		});
	}

	public String getOutputPath() {
		return this.textExportDirectory.getText();
	}

	public String getTemplatePath() {
		return this.textTemplateDirectory.getText();
	}

	protected void initBoutonParcourirRepertoireExport() {
		this.buttonExportDirectoryBrowse.addActionListener(createFileListener(
				this, this.textExportDirectory, false));
	}

	protected void initBoutonParcourirRepertoireTemplate() {
		this.buttonTemplateDirectoryBrowse
				.addActionListener(createFileListener(this,
						this.textTemplateDirectory, true));
	}

	protected void initChampRepertoireExport() {
		applyStyle(this.textExportDirectory);
	}

	protected void initChampRepertoireTemplate() {
		applyStyle(this.textTemplateDirectory);
	}

	protected void applyStyle(JTextField field) {
		field.setMaximumSize(new Dimension(350, 20));
		field.setEditable(false);
	}

	protected ActionListener createFileListener(final Component parent,
			final JTextField target, final boolean refresh) {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File f = fileChooser.getSelectedFile();
					target.setText(f.getAbsolutePath());
					if (refresh) {
						getController().updateTemplatesAndMacrosCount(
								f.getAbsolutePath());
					}
				}

			}
		};
		return listener;
	}

	@SuppressWarnings("unchecked")
	private CodeGenerationController getController() {
		return ((IView<CodeGenerationController>) getParent()).getController();
	}

	public void setTemplatesAndMacrosCount(Integer macros, Integer templates) {
		this.labelMacrosCount.setText(macros + " "
				+ BundleUtils.getMessage("msg_macros_found"));
		if (macros > 0) {
			applySuccessStyle(this.labelMacrosCount);
		} else {
			applyErrorStyle(this.labelMacrosCount);
		}

		this.labelTemplatesCount.setText(templates + " "
				+ BundleUtils.getMessage("msg_templates_found"));
		if (templates > 0) {
			applySuccessStyle(this.labelTemplatesCount);
		} else {
			applyErrorStyle(this.labelTemplatesCount);
		}
		repaint();
		updateUI();
	}

	private void applyErrorStyle(final JLabel label) {
		Font f = label.getFont().deriveFont(Font.PLAIN);
		label.setForeground(Color.RED);
		label.setFont(f);
	}

	private void applySuccessStyle(final JLabel label) {
		Font f = label.getFont().deriveFont(Font.BOLD);
		label.setForeground(new Color(0.2f, 0.2f, 0.2f));
		label.setFont(f);
	}
	
  
    public void setDefaultBrowsePath(String defaultBrowsePath) {
        if(defaultBrowsePath != null) {
            this.fileChooser.setCurrentDirectory(new File(defaultBrowsePath));
        }
    }

}

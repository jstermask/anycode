package in.labulle.anycode.astah.plugin.generator.ui.tab;

import in.labulle.anycode.astah.plugin.generator.control.CodeGenerationController;
import in.labulle.anycode.astah.plugin.generator.control.ICodeGenerationContext;
import in.labulle.anycode.astah.plugin.generator.service.impl.ClassCodeGenerationServiceImpl;
import in.labulle.anycode.astah.plugin.model.ModelRepositoryImpl;
import in.labulle.anycode.astah.plugin.mvc.IView;
import in.labulle.anycode.astah.plugin.osgi.util.BundleUtils;
import in.labulle.anycode.astah.plugin.report.ICodeGenerationLog;
import in.labulle.anycode.astah.plugin.template.repository.impl.TemplateRepositoryFactoryImpl;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

import com.change_vision.jude.api.inf.ui.IPluginExtraTabView;
import com.change_vision.jude.api.inf.ui.ISelectionListener;

public class CodeConfigurationTabView extends JPanel implements
		IPluginExtraTabView, ICodeGenerationContext, IView<CodeGenerationController> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8083632372696277339L;
	
	/**
	 * configuration panel.
	 */
	private ConfigurationPanel configurationPanel;
	
	/**
	 * report panel.
	 */
	private ReportPanel reportPanel;
	

	
	private CodeGenerationController controller;
	
	public CodeConfigurationTabView() {
		super();
		CodeGenerationController c = new CodeGenerationController(new ClassCodeGenerationServiceImpl(new ModelRepositoryImpl(), new TemplateRepositoryFactoryImpl()));
		register(c);
		initComponents();
	}
	
	private void initComponents() {
		configurationPanel = new ConfigurationPanel();
		reportPanel = new ReportPanel();
		this.setLayout(new BorderLayout()); 
		this.add(configurationPanel, BorderLayout.WEST);
		this.add(reportPanel, BorderLayout.CENTER);		
	}

	@Override
	public void activated() {
		// TODO Auto-generated method stub

	}


	@Override
	public void addSelectionListener(ISelectionListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivated() {
		// TODO Auto-generated method stub

	}

	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public String getDescription() {
		return BundleUtils.getMessage("configuration_tab_description");
	}

	@Override
	public String getTitle() {
		return BundleUtils.getMessage("configuration_tab_title");
	}

	@Override
	public String getTemplatePath() {
		return configurationPanel.getTemplatePath();
	}

	@Override
	public String getOutputPath() {
		return configurationPanel.getOutputPath();
	}

	@Override
	public CodeGenerationController getController() {
		return this.controller;
	}

	@Override
	public void register(CodeGenerationController controller) {
		this.controller = controller;
		controller.register(this);
	}

	@Override
	public void addError(String msg) {

		
	}

	@Override
	public ICodeGenerationLog getLog() {
		return this.reportPanel.getCodeGenerationLogTableModel();
	}

	@Override
	public void setTemplatesAndMacrosCount(Integer macros, Integer templates) {
		this.configurationPanel.setTemplatesAndMacrosCount(macros, templates);
		
	}

	

}

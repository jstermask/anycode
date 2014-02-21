package in.labulle.anycode.astah.plugin.generator.ui;

import in.labulle.anycode.astah.plugin.osgi.util.BundleUtils;
import in.labulle.anycode.astah.plugin.osgi.util.ImageBundle;
import in.labulle.anycode.engine.log.ICodeGenerationEvent;
import in.labulle.anycode.engine.log.impl.CodeGenerationLog;

import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class CodeGenerationLogTableModel extends CodeGenerationLog implements
		TableModel {
	private final ImageIcon successIcon = new ImageIcon(ImageBundle.getBundle()
			.getImageURL("success.gif"));
	private final ImageIcon failureIcon = new ImageIcon(ImageBundle.getBundle()
			.getImageURL("failure.gif"));

	private final String[] columnNames = { "status", "template", "uml_element",
			"details" };

	private final Class<?>[] columnClasses = { ImageIcon.class, String.class,
			String.class, String.class };

	private final Set<TableModelListener> listeners = new HashSet<TableModelListener>();

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClasses[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return BundleUtils.getMessage("table_header_"
				+ columnNames[columnIndex]);
	}

	@Override
	public int getRowCount() {
		return super.getContent().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ICodeGenerationEvent event = super.getContent().get(rowIndex);
		switch (columnIndex) {
		case 1:
			return event.getTemplateName();
		case 2:
			return event.getUmlElementName();
		case 0:
			switch (event.getStatus()) {
			case FAILURE:
				return failureIcon;
			case SUCCESS:
				return successIcon;
			case IN_PROGRESS:
				return null;
			}
		case 3:
			return event.getDetails();
		}

		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		this.listeners.remove(l);
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		this.listeners.add(l);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
	}

}

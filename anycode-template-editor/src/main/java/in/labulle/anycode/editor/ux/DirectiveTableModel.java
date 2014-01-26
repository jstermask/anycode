package in.labulle.anycode.editor.ux;

import in.labulle.anycode.editor.core.Directive;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class DirectiveTableModel implements TableModel {
	private Directive directive;
	
	public DirectiveTableModel(final Directive directive) {
		this.directive = directive;
	}

	@Override
	public int getRowCount() {
		return directive.getElements().size();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return "Function/Macro";
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.directive.getElements().get(rowIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	@Override
	public void addTableModelListener(TableModelListener l) {

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		

	}

}

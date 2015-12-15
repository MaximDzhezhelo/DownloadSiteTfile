package ua.kiev.makson.gui.panel.table;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Id", "Name", "Data" };
	private static final Class<?>[] COLUMN_TYPES = new Class<?>[] { Integer.class, String.class, String.class };

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return COLUMN_NAMES[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_TYPES[columnIndex];
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		switch (columnIndex) {
		case 0:
			return rowIndex;
		case 1:
			return "Text for " + rowIndex;
		case 2:
			return "Text for " + rowIndex;

		default:
			return "Error";
		}
	}
}

package ua.kiev.makson.gui.panel.table;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Id", "Name", "Data" };
	private static final Class<?>[] COLUMN_TYPES = new Class<?>[] { Integer.class, String.class, String.class };
	private List<String> list;

	public TableModel() {
		list = new ArrayList<>();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return list.size();
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
			return rowIndex + 1;
		case 1:
			return list.get(rowIndex).toString();
		case 2:
			return getDate();

		default:
			return "Error";
		}
	}

	private String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		return dateFormat.format(new Date());
	}

	public void updateData(String str) {
		list.add(str);
		fireTableDataChanged();
	}

	public void cleanData() {
		list.clear();
		fireTableDataChanged();
	}

}

package ua.kiev.makson.gui.panel.table;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import ua.kiev.makson.gui.MyFrame;

public class TablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private MyFrame frame;
	private JTable table;
	private TableModel tableModel;

	public TablePanel(MyFrame frame) {
		this.frame = frame;
	}

	public void createTablePanel() {
		tableModel = new TableModel();
		table = new JTable(tableModel);
		table.setEnabled(false);
		changeTableColumn();
		// table.setDefaultRenderer(1, renderer);
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	private void changeTableColumn() {
		TableColumn column0 = table.getColumnModel().getColumn(0);
		TableColumn column2 = table.getColumnModel().getColumn(2);
		column0.setMaxWidth(25);
		column2.setMaxWidth(150);
	}

}

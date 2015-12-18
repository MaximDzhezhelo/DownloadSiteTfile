package ua.kiev.makson.gui.panel.table;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import ua.kiev.makson.gui.MyFrame;

public class TablePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	// private MyFrame frame;
	private JTable table;
	private TableModel tableModel;

	public TablePanel(MyFrame frame) {
		// this.frame = frame;
	}

	public TableModel getTableModel() {
		return tableModel;
	}

	public void createTablePanel() {
		tableModel = new TableModel();
		table = new JTable(tableModel);
		table.setEnabled(false);
		changeTableColumn();
		JButton button = new JButton("Clean");
		button.addActionListener(this);
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(button, BorderLayout.LINE_START);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	private void changeTableColumn() {
		TableColumn column0 = table.getColumnModel().getColumn(0);
		TableColumn column2 = table.getColumnModel().getColumn(2);
		MyRenderer myRenderer = new MyRenderer();

		column0.setMaxWidth(35);
		column0.setCellRenderer(myRenderer);
		column2.setMaxWidth(150);
		column2.setCellRenderer(myRenderer);
	}

	@Override
	public void actionPerformed(ActionEvent ex) {
		if (ex == null) {
			return;
		}
		if (ex.getSource() instanceof JButton) {
			JButton buton = (JButton) ex.getSource();
			if (buton.getText().equals("Clean")) {
				tableModel.cleanData();
			}
		}
	}

}

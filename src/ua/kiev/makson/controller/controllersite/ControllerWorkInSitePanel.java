package ua.kiev.makson.controller.controllersite;

import javax.swing.JTextField;

import ua.kiev.makson.gui.panel.table.TableModel;

public class ControllerWorkInSitePanel {
	private JTextField count;
	private JTextField leftTime;
	private JTextField loading;
	private TableModel tableModel;

	public JTextField getCount() {
		return count;
	}

	public void setCount(JTextField count) {
		this.count = count;
	}

	public JTextField getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(JTextField leftTime) {
		this.leftTime = leftTime;
	}

	public JTextField getLoading() {
		return loading;
	}

	public void setLoading(JTextField loading) {
		this.loading = loading;
	}

	public TableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(TableModel tableModel) {
		this.tableModel = tableModel;
	}

}

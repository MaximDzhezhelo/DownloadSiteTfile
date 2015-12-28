package ua.kiev.makson.gui.panel.workinsitepanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.kiev.makson.controller.Controller;
import ua.kiev.makson.controller.controllersite.ControllerWorkInSitePanel;
import ua.kiev.makson.gui.MyFrame;
import ua.kiev.makson.gui.panel.table.TableModel;
import ua.kiev.makson.gui.panel.table.TablePanel;

public class TextFieldPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField count;
	private JTextField leftTime;
	private JTextField loading;
	private JTextField urlDownload;
	private JPanel panelText;
	private JPanel download;
	private MyFrame frame;
	private Controller control;
	private ControllerWorkInSitePanel inSitePanel;
	private TablePanel tablePanel;

	public TextFieldPanel(MyFrame frame, TablePanel tablePanel) {
		this.frame = frame;
		this.tablePanel = tablePanel;
		control = frame.getControl();
		inSitePanel = control.getControllerWorkInSitePanel();
	}

	public void createPanel() {
		createTextFieldPanel();
		createUrlDownloadPanel();
		setLayout(new BorderLayout());
		this.add(download, BorderLayout.CENTER);
		this.add(panelText, BorderLayout.AFTER_LAST_LINE);
	}

	public void createTextFieldPanel() {
		panelText = new JPanel();
		JLabel labelCount = new JLabel("Count");
		count = new JTextField(10);
		setParametrsField(count);
		JLabel labelTime = new JLabel("LeftTime");
		leftTime = new JTextField(10);
		setParametrsField(leftTime);
		JLabel labelLoad = new JLabel("Loading");
		loading = new JTextField(10);
		setParametrsField(loading);
		panelText.add(labelCount);
		panelText.add(count);
		panelText.add(labelTime);
		panelText.add(leftTime);
		panelText.add(labelLoad);
		panelText.add(loading);
	}

	public void createUrlDownloadPanel() {
		download = new JPanel();
		urlDownload = new JTextField(45);
		urlDownload.setText("http://tfile.me/forum/viewforum.php?f=4");
		download.add(urlDownload);
	}

	private void setParametrsField(JTextField field) {
		field.setEnabled(false);
		field.setHorizontalAlignment(JTextField.CENTER);
		field.setBackground(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
	}

	public void setControllerWorkInSitePanel() {
		TableModel tableModel = tablePanel.getTableModel();
		String download = checkUrlDownload();
		inSitePanel.setCount(count);
		inSitePanel.setLeftTime(leftTime);
		inSitePanel.setLoading(loading);
		inSitePanel.setDownload(download);
		inSitePanel.setTableModel(tableModel);
	}

	private String checkUrlDownload() {
		String download = urlDownload.getText();
		if (download.isEmpty() || download == null || download.trim().length() < 10) {
			urlDownload.setText("Enter site PLEASE");
			urlDownload.setBackground(Color.RED);
			stopWorkingSite();
			return null;
		}
		return download;
	}

	private void stopWorkingSite() {
		control = frame.getControl();
		control.stopToTheSite();
	}
}

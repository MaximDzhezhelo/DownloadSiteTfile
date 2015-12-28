package ua.kiev.makson.gui.panel.workinsitepanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.kiev.makson.controller.Controller;
import ua.kiev.makson.gui.MyFrame;
import ua.kiev.makson.gui.panel.table.TableModel;
import ua.kiev.makson.gui.panel.table.TablePanel;

public class ButtonPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton butStart;
	private JButton butStop;
	private MyFrame frame;
	private Controller control;
	private JTextField count;
	private JTextField leftTime;
	private JTextField loading;
	private JTextField urlDownload;
	private JPanel panelBut;
	private JPanel panelText;
	private JPanel download;
	private TablePanel tablePanel;

	public ButtonPanel(MyFrame frame, TablePanel tablePanel) {
		this.frame = frame;
		this.tablePanel = tablePanel;
	}

	public void createPanel() {
		createButton();
		createTextFieldPanel();
		createUrlDownloadPanel();
		setLayout(new BorderLayout());
		this.add(panelBut, BorderLayout.BEFORE_FIRST_LINE);
		this.add(download, BorderLayout.CENTER);
		this.add(panelText, BorderLayout.AFTER_LAST_LINE);
	}

	public void createButton() {
		panelBut = new JPanel();
		butStart = new JButton("Start");
		butStart.addActionListener(this);
		butStop = new JButton("Stop");
		butStop.addActionListener(this);
		panelBut.add(butStart);
		panelBut.add(butStop);
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

	private void startWorkingSite() {
		control = frame.getControl();
		TableModel tableModel = tablePanel.getTableModel();
		String download = checkUrlDownload();
		control.goToTheSite(count, leftTime, loading, tableModel, download);
		frame.setControl(control);
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
		frame.setControl(control);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e == null) {
			return;
		}
		if (e.getSource() instanceof JButton) {
			JButton buton = (JButton) e.getSource();
			if (buton.getText().equals("Start")) {
				startWorkingSite();
			} else if (buton.getText().equals("Stop")) {
				stopWorkingSite();
			}
		}
	}
}

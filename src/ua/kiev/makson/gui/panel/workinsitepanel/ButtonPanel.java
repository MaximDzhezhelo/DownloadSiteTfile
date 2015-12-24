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
	private JPanel panelBut;
	private JPanel panelText;
	private TablePanel tablePanel;

	public ButtonPanel(MyFrame frame, TablePanel tablePanel) {
		this.frame = frame;
		this.tablePanel = tablePanel;
	}

	public void createPanel() {
		createButton();
		createTextFieldPanel();
		setLayout(new BorderLayout());
		this.add(panelBut, BorderLayout.CENTER);
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

	private void startWorkingSite() {
		control = frame.getControl();
		TableModel tableModel = tablePanel.getTableModel();
		control.goToTheSite(count, leftTime, loading, tableModel);
		frame.setControl(control);
	}

	private void stopWorkingSite() {
		control = frame.getControl();
		control.stopToTheSite();
		frame.setControl(control);
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

	private void setParametrsField(JTextField field) {
		field.setEnabled(false);
		field.setHorizontalAlignment(JTextField.CENTER);
		field.setBackground(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));

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

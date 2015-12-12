package ua.kiev.makson.gui.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.kiev.makson.controller.Controller;
import ua.kiev.makson.gui.MyFrame;

public class WorkInSitePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panelBut;
	private JPanel panelText;
	private JButton butStart;
	private JButton butStop;
	private JTextField field1;
	private JTextField field2;
	private JTextField field3;
	private MyFrame frame;
	private Controller control;

	public WorkInSitePanel(MyFrame frame) {
		this.frame = frame;
	}

	public void createPanelWorkInSitePanel() {
		createPanel();
	}

	public void createPanel() {
		panel = new JPanel();
		BorderLayout df = new BorderLayout();
		panel.setLayout(df);
		createButton();
		createTextFieldPanel();
		panel.add(panelBut, BorderLayout.CENTER);
		panel.add(panelText, BorderLayout.AFTER_LAST_LINE);
		this.add(panel);

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
		field1 = new JTextField(10);
		field1.setEnabled(false);
		field2 = new JTextField(10);
		field2.setEnabled(false);
		field3 = new JTextField(10);
		field3.setEnabled(false);
		panelText.add(field1);
		panelText.add(field2);
		panelText.add(field3);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e == null) {
			return;
		}
		if (e.getSource() instanceof JButton) {
			JButton buton = (JButton) e.getSource();
			if (buton.getText().equals("Start")) {
				control = frame.getControl();
				control.goToTheSite();
				frame.setControl(control);
			} else if (buton.getText().equals("Stop")) {
				control = frame.getControl();
				control.stopToTheSite();
				frame.setControl(control);
			}
		}
	}
}

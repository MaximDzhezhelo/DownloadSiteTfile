package ua.kiev.makson.gui.panel.workinsitepanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import ua.kiev.makson.controller.Controller;
import ua.kiev.makson.controller.controllersite.ControllerWorkInSitePanel;
import ua.kiev.makson.gui.MyFrame;

public class ButtonPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton butStart;
	private JButton butStop;
	private JCheckBox editable;
	private MyFrame frame;
	private Controller control;
	private JPanel panelBut;
	private TextFieldPanel fieldPanel;
	private ControllerWorkInSitePanel inSitePanel;

	public ButtonPanel(MyFrame frame, TextFieldPanel fieldPanel) {
		this.frame = frame;
		this.fieldPanel = fieldPanel;
		control = frame.getControl();
		inSitePanel = control.getControllerWorkInSitePanel();
	}

	public void createPanel() {
		createButton();
		setLayout(new BorderLayout());
		this.add(panelBut, BorderLayout.BEFORE_FIRST_LINE);
	}

	public void createButton() {
		panelBut = new JPanel();
		butStart = new JButton("Start");
		butStart.addActionListener(this);
		butStop = new JButton("Stop");
		butStop.addActionListener(this);
		editable = new JCheckBox("Loop");
		editable.addActionListener(this);
		panelBut.add(butStart);
		panelBut.add(butStop);
		panelBut.add(editable);
	}

	private void startWorkingSite() {
		fieldPanel.setControllerWorkInSitePanel();
		control.goToTheSite();
	}

	private void stopWorkingSite() {
		control = frame.getControl();
		control.stopToTheSite();
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
		} else if (e.getSource() instanceof JCheckBox) {
			inSitePanel.setEditable(editable.isSelected());
		}
	}
}

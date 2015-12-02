package ua.kiev.makson.gui.menu.itemlogin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ua.kiev.makson.controller.Controller;
import ua.kiev.makson.gui.MyFrame;

public class MenuPanelLogPass extends JPanel implements ActionListener, Runnable {

	private static final long serialVersionUID = 1L;

	private JPanel panelSite;
	private JPanel panelName;
	private JPanel panelPassword;
	private JPanel panelButton;
	private JTextField siteField;
	private JTextField nameField;
	private JTextField registration;
	private JPasswordField passwordField;
	private JButton push;
	private JButton cancel;
	private MenuLogPassJDialog dialog;
	private MyFrame frame;
	private Controller control;

	public MenuPanelLogPass(MenuLogPassJDialog dialog, MyFrame frame) {
		this.dialog = dialog;
		this.frame = frame;
		this.control = frame.getControl();
	}

	public void setRegistrationColor(Boolean flag) {
		if (flag) {
			registration.setBackground(Color.GREEN);
		} else {
			registration.setBackground(Color.RED);
		}
	}

	public void setRegistrationTime(int time) {
		registration.setText(new Integer(time).toString());
	}

	public void createPanel() {
		createPanelSite();
		createPanelName();
		createPanelPassword();
		createButton();
		Box box = Box.createVerticalBox();
		box.add(panelSite);
		box.add(panelName);
		box.add(panelPassword);
		box.add(panelButton);
		this.add(box);
	}

	public void createPanelSite() {
		panelSite = new JPanel();
		panelSite.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel labelSite = new JLabel(String.format("%10s", "Site"));
		siteField = new JTextField(20);
		siteField.setText("http://tfile.me/");
		panelSite.add(labelSite);
		panelSite.add(Box.createHorizontalStrut(12));
		panelSite.add(siteField);
	}

	public void createPanelName() {
		panelName = new JPanel();
		panelName.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel labelName = new JLabel(String.format("%10s", "Name"));
		nameField = new JTextField(20);
		nameField.setText("Mq1aximer");
		panelName.add(labelName);
		panelName.add(Box.createHorizontalStrut(12));
		panelName.add(nameField);
	}

	public void createPanelPassword() {
		panelPassword = new JPanel();
		JLabel labelPassword = new JLabel(String.format("%10s", "Password"));
		passwordField = new JPasswordField(20);
		passwordField.setText("asdasdasd");
		panelPassword.add(labelPassword);
		panelPassword.add(Box.createHorizontalStrut(12));
		panelPassword.add(passwordField);
	}

	public void createButton() {
		panelButton = new JPanel();
		push = new JButton("push");
		push.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		registration = new JTextField(5);
		registration.setEnabled(false);
		registration.setBackground(Color.RED);
		panelButton.add(push, BorderLayout.WEST);
		panelButton.add(registration, BorderLayout.CENTER);
		panelButton.add(cancel, BorderLayout.EAST);

	}

	public void sendLogPass() {
		String urlString = siteField.getText();
		String login = nameField.getText();
		char[] pas = passwordField.getPassword();
		control = frame.getControl();
		control.sendLogPassUrl(urlString, login, pas);
		boolean flag = control.setRegistrationColor();
		setRegistrationColor(flag);
		frame.setControl(control);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e == null) {
			return;
		}
		JButton button = (JButton) e.getSource();
		if (button.getText().equals("push")) {
			new Thread(this).start();
		} else if (button.getText().equals("Cancel")) {
			dialog.setVisible(false);

		}
	}

	@Override
	public void run() {
		sendLogPass();
	}
}

package ua.kiev.makson.timer;

import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;

import ua.kiev.makson.controller.controllersite.ControllerSite;

public class CountDownAuthentication extends TimerTask {
	private Timer timer;
	private int count;
	private ControllerSite controlSite;
	private JTextField registrationField;
	private boolean killThread;

	public CountDownAuthentication(int count, Timer timer, ControllerSite controlSite) {
		this.count = count;
		this.timer = timer;
		this.controlSite = controlSite;
		this.registrationField = controlSite.getRegistrationField();
	}

	public void setKillThread(boolean killThread) {
		this.killThread = killThread;
	}

	public void remaining() {
		if (killThread || count == 0) {
			timer.cancel();
			registrationField.setText("");
		} else {
			registrationField.setText(new Integer(count).toString());
			count--;
			timer.schedule(new CountDownAuthentication(count, timer, controlSite), 1000);
		}
	}

	@Override
	public void run() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				remaining();
			}
		});
	}
}

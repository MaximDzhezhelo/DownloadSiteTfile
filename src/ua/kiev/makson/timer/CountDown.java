package ua.kiev.makson.timer;

import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;

import ua.kiev.makson.controller.controllersite.ControllerSite;

public class CountDown extends TimerTask {
	private Timer timer;
	private int count;
	private ControllerSite controlSite;
	private JTextField registrationField;

	public CountDown(int count, Timer timer, ControllerSite controlSite) {
		this.count = count;
		this.timer = timer;
		this.controlSite = controlSite;
		this.registrationField = controlSite.getRegistrationField();
	}

	public void remaining() {
		boolean killThread = controlSite.isKillThread();
		if (killThread || count == 0) {
			timer.cancel();
		} else {
			registrationField.setText(new Integer(count).toString());
			count--;
			timer.schedule(new CountDown(count, timer, controlSite), 1000);
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

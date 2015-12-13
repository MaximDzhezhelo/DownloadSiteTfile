package ua.kiev.makson.timer;

import java.awt.EventQueue;
import java.util.Timer;

import javax.swing.JTextField;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.controller.controllersite.ControllerWorkInSitePanel;

public class CountDownDownloadVideo implements Runnable {
	private Timer timer;
	private int count;
	private ControllerSite controlSite;
	private ControllerWorkInSitePanel controllerWorkInSitePanel;
	private JTextField leftTimeField;

	public CountDownDownloadVideo(int count, Timer timer, ControllerSite controlSite) {
		this.count = count;
		this.timer = timer;
		this.controlSite = controlSite;
		controllerWorkInSitePanel = controlSite.getControllerWorkInSitePanel();
		leftTimeField = controllerWorkInSitePanel.getLeftTime();
	}

	public void remaining() {
		// boolean killThread = controlSite.isKillThread();
		boolean killThread = false;
		if (killThread || count == 0) {
			timer.cancel();
		} else {
			leftTimeField.setText(new Integer(count).toString());
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

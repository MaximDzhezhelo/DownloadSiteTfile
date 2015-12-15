package ua.kiev.makson.timer;

import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.controller.controllersite.ControllerWorkInSitePanel;

public class CountDownDownloadVideo extends TimerTask {
	private Timer timer;
	private int count;
	private ControllerSite controlSite;
	private ControllerWorkInSitePanel controllerWorkInSitePanel;
	private JTextField leftTimeField;
	private boolean killThread;

	public CountDownDownloadVideo(int count, Timer timer, ControllerSite controlSite) {
		this.count = count;
		this.timer = timer;
		this.controlSite = controlSite;
		controllerWorkInSitePanel = controlSite.getControllerWorkInSitePanel();
		leftTimeField = controllerWorkInSitePanel.getLeftTime();
	}

	public void setKillThread(boolean doGetVideo) {
		this.killThread = doGetVideo;
	}

	public void remaining() {
		if (killThread || count == 0) {
			timer.cancel();
		} else {
			leftTimeField.setText(new Integer(count).toString());
			count--;
			timer.schedule(new CountDownDownloadVideo(count, timer, controlSite), 1000);
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

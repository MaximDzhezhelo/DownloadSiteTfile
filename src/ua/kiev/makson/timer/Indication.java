package ua.kiev.makson.timer;

import java.util.Timer;

import ua.kiev.makson.controller.controllersite.ControllerSite;

public class Indication {
	private CountDownDownloadVideo down;

	/*
	 * creates a Timer Object, CountDown Object. begins the process of the count
	 * down in Authentication.
	 */
	public void startIndicationAuthentication(int time, ControllerSite controlSite) {
		Timer timer = new Timer();
		CountDownAuthentication down = new CountDownAuthentication(time, timer, controlSite);
		down.remaining();
	}

	/*
	 * creates a Timer Object, CountDown Object. begins the process of the count
	 * down in DownloadVideo.
	 */
	public void startIndicationDownloadVideo(int time, ControllerSite controlSite) {
		Timer timer = new Timer();
		down = new CountDownDownloadVideo(time, timer, controlSite);
		down.remaining();
	}

	public void stopCountDownDownloadVideo(boolean doGetVideo) {
		down.setDoGetVideo(doGetVideo);
	}
}

package ua.kiev.makson.timer;

import java.util.Timer;

import ua.kiev.makson.controller.controllersite.ControllerSite;

public class Indication {
	private CountDownDownloadVideo downDownloadVideo;
	private CountDownAuthentication downAuthentication;

	/*
	 * creates a Timer Object, CountDown Object. begins the process of the count
	 * down in Authentication.
	 */
	public void startIndicationAuthentication(int time, ControllerSite controlSite) {
		Timer timer = new Timer();
		downAuthentication = new CountDownAuthentication(time, timer, controlSite);
		downAuthentication.remaining();
	}

	/*
	 * creates a Timer Object, CountDown Object. begins the process of the count
	 * down in DownloadVideo.
	 */
	public void startIndicationDownloadVideo(int time, ControllerSite controlSite) {
		Timer timer = new Timer();
		downDownloadVideo = new CountDownDownloadVideo(time, timer, controlSite);
		downDownloadVideo.remaining();
	}

	public void stopCountDownDownloadVideo(boolean killThread) {
		downDownloadVideo.setKillThread(killThread);
		Timer timer = downDownloadVideo.getTimer();
		timer.cancel();
	}

	public void stopCountDownAuthenticatio(boolean killThread) {
		downAuthentication.setKillThread(killThread);
	}
}

package ua.kiev.makson.timer;

import java.util.Timer;

import ua.kiev.makson.controller.controllersite.ControllerSite;

public class Indication {

	/*
	 * creates a Timer Object, CountDown Object. begins the process of the count
	 * down.
	 */
	public void startIndication(int time, ControllerSite controlSite) {
		Timer timer = new Timer();
		CountDown down = new CountDown(time, timer, controlSite);
		down.remaining();
	}
}

package ua.kiev.makson.timer;

import java.util.Timer;
import java.util.TimerTask;

import ua.kiev.makson.controller.controllersite.ControllerSite;

public class CountDown extends TimerTask {
	private Timer timer;
	private int count;
	private ControllerSite controlSite;

	public CountDown(int count, Timer timer, ControllerSite controlSite) {
		this.count = count;
		this.timer = timer;
		this.controlSite = controlSite;
	}

	public void remaining() {
		if (count == 0) {
			timer.cancel();
		} else {
			count--;
			timer.schedule(new CountDown(count, timer, controlSite), 1000);
		}
	}

	@Override
	public void run() {
		remaining();
	}

}

package ua.kiev.makson.work_in_site.requests;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.timer.Indication;
import ua.kiev.makson.timer.RandomTime;

public class RequestGetVideoStart implements Callable<Integer> {
	private Request request;
	private ControllerSite controlSite;
	private GeneralHttpClient genClient;
	private ScheduledExecutorService executor;
	private ScheduledFuture<Integer> future;
	private boolean doGetVideo;
	private RandomTime randomTime;
	private int time;
	private Indication indicate;
	private static final Logger LOGGER = Logger.getLogger(RequestGetVideoStart.class.getName());

	public RequestGetVideoStart(ControllerSite controlSite, GeneralHttpClient genClient) {
		this.controlSite = controlSite;
		this.genClient = genClient;
		executor = Executors.newScheduledThreadPool(1);
		randomTime = new RandomTime();
	}

	public void setDoGetVideo(boolean doGetVideo) {
		this.doGetVideo = doGetVideo;
	}

	public void getAuthenticationAndGetVideoVideo() {
		request = new Request();
		request.authentication(genClient, controlSite);
		request.getVideo(genClient, controlSite);
	}

	public void loopRequests() {
		if (!doGetVideo) {
			time = randomTime.getRandomGetRequests();
			LOGGER.log(Level.SEVERE, "time in loopRequests() " + time);
			indication(time, controlSite);
			future = executor.schedule(this, time, TimeUnit.SECONDS);
			try {
				future.get();
			} catch (InterruptedException | ExecutionException ex) {
				LOGGER.log(Level.SEVERE, ex.getMessage());
			}
		} else if (doGetVideo) {
			stopDownload();
		} else {
			loopRequests();
		}
	}

	private void indication(int time, ControllerSite controlSite) {
		indicate = new Indication();
		indicate.startIndicationDownloadVideo(time, controlSite);
	}

	@Override
	public Integer call() throws Exception {
		getAuthenticationAndGetVideoVideo();
		return null;
	}

	public void stopDownload() {
		System.out.println("RequestGetVideoStart stop Download");
		
		LOGGER.log(Level.SEVERE, "loopRequests() executor shut Down");
		if (!executor.isShutdown()) {
			executor.shutdown();
		}
		indicate.stopCountDownDownloadVideo(doGetVideo);
		request.stopDownload();

	}
}

package ua.kiev.makson.work_in_site.requests.getvideo;

import java.util.Map;
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
import ua.kiev.makson.work_in_site.requests.GeneralHttpClient;
import ua.kiev.makson.work_in_site.requests.Request;

public class StartGetVideo implements Runnable {
	private Request request;
	private GeneralHttpClient genClient;
	private ControllerSite controlSite;
	private ScheduledExecutorService executor;
	private ScheduledFuture<Integer> future;
	private boolean doGetVideo;
	private int time;
	private VideoDownloader downloader;
	private Map<String, String> header;
	private static final Logger LOGGER = Logger.getLogger(StartGetVideo.class.getName());

	public StartGetVideo(GeneralHttpClient genClient, ControllerSite controlSite, Map<String, String> header,
			Request request) {
		this.request = request;
		this.genClient = genClient;
		this.controlSite = controlSite;
		this.header = header;
		executor = Executors.newScheduledThreadPool(1);
		doGetVideo = true;
	}

	public void getVideo() {
		if (downloader == null) {
			downloader = new VideoDownloader(genClient, controlSite, header);
		}
		try {
			loopRequests();
		} catch (InterruptedException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage());
			actionsAfterErrorStartVideoDownload(genClient, controlSite);
		} catch (ExecutionException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage());
			actionsAfterErrorStartVideoDownload(genClient, controlSite);
		}
	}

	/*
	 * Method in cocurrent processes the request startVideoDownload ().
	 * Initially triggered a timer to 2 seconds, then the cycle is triggered
	 * timer with random time, then the program and re-authenticates all
	 * requests are processed.
	 */
	private void loopRequests() throws InterruptedException, ExecutionException {
		RandomTime randomTime = new RandomTime();
		timer(randomTime);
		future = executor.schedule(downloader, time, TimeUnit.SECONDS);
		future.get();
		if (!doGetVideo) {
			LOGGER.log(Level.SEVERE, "loopRequests()");
			executor.shutdownNow();
		} else {
			LOGGER.log(Level.SEVERE, "loopRequests() again ");
			timer(randomTime);
			request.authentication(genClient, controlSite);
			loopRequests();
		}
	}

	private void timer(RandomTime randomTime) {
		time = randomTime.getRandomGetRequests();
		LOGGER.log(Level.SEVERE, "randomTime " + time);
		indication(time, controlSite);
	}

	private void actionsAfterErrorStartVideoDownload(GeneralHttpClient genClient, ControllerSite controlSite) {
		request.authentication(genClient, controlSite);
		request.getVideo(genClient, controlSite);
	}

	private void indication(int time, ControllerSite controlSite) {
		Indication indicate = new Indication();
		indicate.startIndicationDownloadVideo(time, controlSite);
	}

	@Override
	public void run() {
		getVideo();
	}
}

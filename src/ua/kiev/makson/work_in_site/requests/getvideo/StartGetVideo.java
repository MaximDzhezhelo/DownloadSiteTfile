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
	private RandomTime randomTime;
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
		randomTime = new RandomTime();
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

	private void loopRequests() throws InterruptedException, ExecutionException {
		time = randomTime.getRandomGetRequests();
		LOGGER.log(Level.SEVERE, "randomTime " + time);
		future = executor.schedule(downloader, time, TimeUnit.SECONDS);
		future.get();
		if (!doGetVideo) {
			LOGGER.log(Level.SEVERE, "loopRequests()");
			executor.shutdownNow();
		} else {
			LOGGER.log(Level.SEVERE, "loopRequests() again ");
			loopRequests();
		}
	}

	private void actionsAfterErrorStartVideoDownload(GeneralHttpClient genClient, ControllerSite controlSite) {
		request.authentication(genClient, controlSite);
		request.getVideo(genClient, controlSite);
	}

	@Override
	public void run() {
		getVideo();
	}
}

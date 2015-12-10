package ua.kiev.makson.work_in_site.requests;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.timer.RandomTime;
import ua.kiev.makson.work_in_site.requests.authentication.Authentication;
import ua.kiev.makson.work_in_site.requests.getvideo.VideoDownloader;

public class Request {
	private RequestHelper requestHelper;
	private Authentication authentication;
	private VideoDownloader downloader;
	private Map<String, String> header;
	private ScheduledExecutorService executor;
	private ScheduledFuture<Integer> future;
	private boolean doGetVideo;
	private RandomTime randomTime;
	private int time;
	private static final Logger LOGGER = Logger.getLogger(Request.class.getName());

	public Request() {
		requestHelper = new RequestHelper();
		header = requestHelper.getInitialRequestHeader();
		doGetVideo = true;
	}

	public void setDoGetVideo(boolean doGetVideo) {
		this.doGetVideo = doGetVideo;
	}

	public void authentication(GeneralHttpClient genClient, ControllerSite controlSite) {
		if (authentication == null) {
			authentication = new Authentication();
			authentication.startAuthentication(genClient, controlSite, requestHelper);
		}
	}

	public void getVideo(GeneralHttpClient genClient, ControllerSite controlSite) {
		if (downloader == null) {
			downloader = new VideoDownloader(genClient, controlSite, header);
		}
		try {
			loopRequests();
		} catch (InterruptedException | ExecutionException e) {
			actionsAfterErrorStartVideoDownload(genClient, controlSite);
		}
	}

	private void actionsAfterErrorStartVideoDownload(GeneralHttpClient genClient, ControllerSite controlSite) {
		authentication(genClient, controlSite);
		getVideo(genClient, controlSite);
	}

	private void loopRequests() throws InterruptedException, ExecutionException {
		time = randomTime.getRandomGetRequests();
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
}

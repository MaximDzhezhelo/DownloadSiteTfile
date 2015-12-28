package ua.kiev.makson.work_in_site.requests.getvideo;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.controller.controllersite.ControllerWorkInSitePanel;
import ua.kiev.makson.timer.RandomTime;
import ua.kiev.makson.work_in_site.requests.GeneralHttpClient;
import ua.kiev.makson.work_in_site.requests.RequesAssistant;
import ua.kiev.makson.work_in_site.requests.getvideo.page.GeneralWorkInSite;

public class VideoDownloader {

	private GeneralHttpClient genClient;
	private ControllerSite controlSite;
	private Map<String, String> header;
	private GeneralWorkInSite generalWorkInSite;
	private ScheduledExecutorService executor;
	private ScheduledFuture<Integer> future;
	private RandomTime randomTime;
	private int time;
	private GetRequests get;
	private int statusLine;
	private static final Logger LOGGER = Logger.getLogger(VideoDownloader.class);

	public VideoDownloader(GeneralHttpClient genClient, ControllerSite controlSite, Map<String, String> header) {
		executor = Executors.newScheduledThreadPool(1);
		randomTime = new RandomTime();
		this.genClient = genClient;
		this.controlSite = controlSite;
		this.header = header;

	}

	public void startVideoDownload() throws InterruptedException, ExecutionException {

		String url = getUrlDownload();
		LOGGER.info("run start Video Download");

		RequesAssistant assistant = new RequesAssistant(genClient, controlSite, header);

		get = new GetRequests(url, assistant);
		statusLine = callGetAfterAuthentication();

		generalWorkInSite = new GeneralWorkInSite();
		generalWorkInSite.parsingPage(assistant);
	}

	private int callGetAfterAuthentication() throws InterruptedException, ExecutionException {
		time = randomTime.getRandomTimeAuthentication();
		future = executor.schedule(get, time, TimeUnit.SECONDS);
		statusLine = future.get();
		if (statusLine == 200) {
			LOGGER.info("executor shutdown");
			executor.shutdownNow();
			return statusLine;
		} else {
			LOGGER.info("statusLine !==200 run again callGetAfterAuthentication() ");
			callGetAfterAuthentication();
		}
		return statusLine;
	}

	private String getUrlDownload() {
		ControllerWorkInSitePanel controllerWorkInSitePanel = controlSite.getControllerWorkInSitePanel();
		return controllerWorkInSitePanel.getDownload();

	}

	public void stopDownload() {
		if (!executor.isShutdown()) {
			LOGGER.info("executor VideoDownloader shutdownNow");
			executor.shutdownNow();
		}
		generalWorkInSite.stopDownload();
	}

}

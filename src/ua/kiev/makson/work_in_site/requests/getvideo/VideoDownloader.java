package ua.kiev.makson.work_in_site.requests.getvideo;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.timer.RandomTime;
import ua.kiev.makson.work_in_site.page.GeneralWorkInSite;
import ua.kiev.makson.work_in_site.requests.GeneralHttpClient;
import ua.kiev.makson.work_in_site.requests.RequesAssistant;

public class VideoDownloader {

	private ScheduledExecutorService executor;
	private ScheduledFuture<Integer> future;
	private RandomTime randomTime;
	private int time;
	private GetRequests get;
	private int statusLine;
	private static final Logger LOGGER = Logger.getLogger(VideoDownloader.class.getName());

	public void startVideoDownload(GeneralHttpClient genClient, ControllerSite controlSite,
			Map<String, String> header) {

		String url = "http://tfile.me/forum/viewforum.php?f=4";
		LOGGER.log(Level.SEVERE, "run start Video Download");

		RequesAssistant assistant = new RequesAssistant(genClient, controlSite, header);

		get = new GetRequests(url, assistant);
		try {

			statusLine = callGetAfterAuthentication();
			GeneralWorkInSite generalWorkInSite = new GeneralWorkInSite();
			generalWorkInSite.parsingPage(assistant);

		} catch (InterruptedException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage());
		} catch (ExecutionException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage());
		} finally {
			LOGGER.log(Level.SEVERE, "executor shutdown");
			executor.shutdownNow();
		}

	}

	private int callGetAfterAuthentication() throws InterruptedException, ExecutionException {
		time = randomTime.getRandomTimeAuthentication();
		future = executor.schedule(get, time, TimeUnit.SECONDS);
		statusLine = future.get();
		if (statusLine == 200) {
			return statusLine;
		} else {
			LOGGER.log(Level.SEVERE, "statusLine !==200 run again callGetAfterAuthentication() ");
			callGetAfterAuthentication();
		}
		return statusLine;

	}
}

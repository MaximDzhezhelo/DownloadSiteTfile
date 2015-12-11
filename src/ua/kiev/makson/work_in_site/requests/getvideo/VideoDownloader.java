package ua.kiev.makson.work_in_site.requests.getvideo;

import java.util.Map;
import java.util.concurrent.Callable;
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
import ua.kiev.makson.work_in_site.requests.RequesAssistant;
import ua.kiev.makson.work_in_site.requests.getvideo.page.GeneralWorkInSite;

public class VideoDownloader implements Callable<Integer> {

	private GeneralHttpClient genClient;
	private ControllerSite controlSite;
	private Map<String, String> header;
	private ScheduledExecutorService executor;
	private ScheduledFuture<Integer> future;
	private RandomTime randomTime;
	private int time;
	private GetRequests get;
	private int statusLine;
	private static final Logger LOGGER = Logger.getLogger(VideoDownloader.class.getName());

	public VideoDownloader(GeneralHttpClient genClient, ControllerSite controlSite, Map<String, String> header) {
		executor = Executors.newScheduledThreadPool(1);
		randomTime = new RandomTime();
		this.genClient = genClient;
		this.controlSite = controlSite;
		this.header = header;

	}

	public void startVideoDownload() throws InterruptedException, ExecutionException {

		String url = "http://tfile.me/forum/viewforum.php?f=4";
		LOGGER.log(Level.SEVERE, "run start Video Download");

		RequesAssistant assistant = new RequesAssistant(genClient, controlSite, header);

		get = new GetRequests(url, assistant);
		statusLine = callGetAfterAuthentication();

		GeneralWorkInSite generalWorkInSite = new GeneralWorkInSite();
		generalWorkInSite.parsingPage(assistant);

	}

	private int callGetAfterAuthentication() throws InterruptedException, ExecutionException {
		time = randomTime.getRandomTimeAuthentication();
		future = executor.schedule(get, time, TimeUnit.SECONDS);
		statusLine = future.get();
		if (statusLine == 200) {
			LOGGER.log(Level.SEVERE, "executor shutdown");
			executor.shutdownNow();
			return statusLine;
		} else {
			LOGGER.log(Level.SEVERE, "statusLine !==200 run again callGetAfterAuthentication() ");
			callGetAfterAuthentication();
		}
		return statusLine;

	}

	@Override
	public Integer call() throws Exception {
		startVideoDownload();
		return null;
	}
}

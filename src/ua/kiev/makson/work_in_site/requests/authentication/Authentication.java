package ua.kiev.makson.work_in_site.requests.authentication;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.timer.Indication;
import ua.kiev.makson.timer.RandomTime;
import ua.kiev.makson.work_in_site.requests.GeneralHttpClient;
import ua.kiev.makson.work_in_site.requests.RequesAssistant;
import ua.kiev.makson.work_in_site.requests.RequestHelper;

public class Authentication implements Callable<Integer> {
	private ScheduledExecutorService executor;
	private RandomTime randomTime;
	private GeneralHttpClient genClient;
	private ControllerSite controlSite;
	private RequestHelper requestHelper;
	private GetAuthentication get;
	private PostAuthentication post;
	private ScheduledFuture<Integer> future;
	private Map<String, String> params;
	private Indication indicate;
	private int time;
	private int statusLine;

	private static final Logger LOGGER = Logger.getLogger(Authentication.class);

	public Authentication(GeneralHttpClient genClient, ControllerSite controlSite, RequestHelper requestHelper) {
		this.genClient = genClient;
		this.controlSite = controlSite;
		this.requestHelper = requestHelper;
		executor = Executors.newScheduledThreadPool(1);
		randomTime = new RandomTime();
	}

	public int getStatusLine() {
		return statusLine;
	}

	public RandomTime getRandomTime() {
		return randomTime;
	}

	public void startAuthentication() {
		String url = controlSite.getUrl();
		Map<String, String> header = requestHelper.getInitialRequestHeader();
		RequesAssistant assistant = new RequesAssistant(genClient, controlSite, header);

		get = new GetAuthentication(url, assistant);

		try {
			statusLine = callGet(controlSite);

			params = requestHelper.getPostParamsForLogin(controlSite);
			url = "http://tfile.me/login/login.php";
			post = new PostAuthentication(url, header, params, genClient);
			statusLine = callPost(controlSite);

			controlSite.setRegistration(statusLine == 302);

		} catch (InterruptedException | ExecutionException ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			LOGGER.info("executor shutdown");
			executor.shutdownNow();
		}

	}

	private int callGet(ControllerSite controlSite) throws InterruptedException, ExecutionException {
		LOGGER.info("callGet ");
		time = randomTime.getRandomTimeAuthentication();
		indication(time, controlSite);
		LOGGER.info("randomTime " + time);
		future = executor.schedule(get, time, TimeUnit.SECONDS);
		statusLine = future.get();
		if (statusLine == 200) {
			return statusLine;
		} else {
			LOGGER.info("statusLine !==200 run again GET ");
			callGet(controlSite);
		}
		return statusLine;
	}

	private int callPost(ControllerSite controlSite) throws InterruptedException, ExecutionException {
		LOGGER.info("callPost ");
		indication(3, controlSite);
		future = executor.schedule(post, 3, TimeUnit.SECONDS);
		statusLine = future.get();
		if (statusLine == 302) {
			return statusLine;
		} else {
			LOGGER.info("statusLine !==302 run again Post ");
			callPost(controlSite);
		}
		return statusLine;
	}

	/*
	 * initializes variables. returns values in a separate thread in the
	 * ControllerSite Object, in which the value setTimeRegistration changes
	 */
	private void indication(int time, ControllerSite controlSite) {
		indicate = new Indication();
		indicate.startIndicationAuthentication(time, controlSite);
	}

	public void stopAuthentication() {
		indicate.stopCountDownAuthenticatio(true);
		controlSite.setRegistration(false);
		if (!executor.isShutdown()) {
			executor.shutdownNow();
		}
	}

	@Override
	public Integer call() throws Exception {
		startAuthentication();
		return statusLine;
	}
}

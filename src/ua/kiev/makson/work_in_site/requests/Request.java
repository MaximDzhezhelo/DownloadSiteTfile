package ua.kiev.makson.work_in_site.requests;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.work_in_site.requests.authentication.Authentication;
import ua.kiev.makson.work_in_site.requests.getvideo.StartGetVideo;

public class Request {
	private ScheduledExecutorService executor;
	private RequestHelper requestHelper;
	private Authentication authentication;
	private StartGetVideo startGetVideo;
	private Map<String, String> header;
	private static final Logger LOGGER = Logger.getLogger(Request.class.getName());

	public Request() {
		requestHelper = new RequestHelper();
		header = requestHelper.getInitialRequestHeader();
	}

	public void authentication(GeneralHttpClient genClient, ControllerSite controlSite) {
		executor = Executors.newScheduledThreadPool(1);
		if (authentication == null) {
			authentication = new Authentication(genClient, controlSite, requestHelper);
		}
		Future<Integer> future = executor.schedule(authentication, 0, TimeUnit.SECONDS);
		try {
			future.get();
		} catch (InterruptedException | ExecutionException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage());
		} finally {
			LOGGER.log(Level.SEVERE, "executor Request authentication shutdown");
			executor.shutdownNow();
		}
	}

	public void getVideo(GeneralHttpClient genClient, ControllerSite controlSite) {
		executor = Executors.newScheduledThreadPool(1);
		if (startGetVideo == null) {
			startGetVideo = new StartGetVideo(genClient, controlSite, header, this);
		}
		Future<Integer> future = executor.schedule(startGetVideo, 0, TimeUnit.SECONDS);
		try {
			future.get();
		} catch (InterruptedException | ExecutionException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage());
		} finally {
			LOGGER.log(Level.SEVERE, "executor Request startGetVideo shutdown");
			executor.shutdownNow();
		}
	}

}

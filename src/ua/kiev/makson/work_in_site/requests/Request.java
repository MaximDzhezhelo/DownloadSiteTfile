package ua.kiev.makson.work_in_site.requests;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.work_in_site.requests.authentication.Authentication;
import ua.kiev.makson.work_in_site.requests.getvideo.VideoDownloader;

public class Request {
	private RequestHelper requestHelper;
	private Authentication authentication;
	private VideoDownloader downloader;
	private Map<String, String> header;
	private static final Logger LOGGER = Logger.getLogger(Request.class.getName());

	public Request() {
		requestHelper = new RequestHelper();
		header = requestHelper.getInitialRequestHeader();
	}

	public void authentication(GeneralHttpClient genClient, ControllerSite controlSite) {
		if (authentication == null) {
			authentication = new Authentication();
			authentication.startAuthentication(genClient, controlSite, requestHelper);
		}
	}

	public void getVideo(GeneralHttpClient genClient, ControllerSite controlSite) {
		if (downloader == null) {
			downloader = new VideoDownloader();
			try {
				downloader.startVideoDownload(genClient, controlSite, header);
			} catch (InterruptedException ex) {
				LOGGER.log(Level.SEVERE, ex.getMessage());
				actionsAfterErrorStartVideoDownload(genClient, controlSite);
			} catch (ExecutionException ex) {
				LOGGER.log(Level.SEVERE, ex.getMessage());
				actionsAfterErrorStartVideoDownload(genClient, controlSite);
			}
		}
	}

	private void actionsAfterErrorStartVideoDownload(GeneralHttpClient genClient, ControllerSite controlSite) {
		authentication(genClient, controlSite);
		getVideo(genClient, controlSite);
	}
}

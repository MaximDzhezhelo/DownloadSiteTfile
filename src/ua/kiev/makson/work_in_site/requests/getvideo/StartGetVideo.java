package ua.kiev.makson.work_in_site.requests.getvideo;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.work_in_site.requests.GeneralHttpClient;

public class StartGetVideo implements Callable<Integer> {
	private GeneralHttpClient genClient;
	private ControllerSite controlSite;
	private VideoDownloader downloader;
	private Map<String, String> header;
	private static final Logger LOGGER = Logger.getLogger(StartGetVideo.class);

	public StartGetVideo(GeneralHttpClient genClient, ControllerSite controlSite, Map<String, String> header) {
		this.genClient = genClient;
		this.controlSite = controlSite;
		this.header = header;
	}

	public void getVideo() {
		if (downloader == null) {
			downloader = new VideoDownloader(genClient, controlSite, header);
		}
		try {
			downloader.startVideoDownload();
		} catch (InterruptedException | ExecutionException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	public void stopDownload() {
		downloader.stopDownload();
	}

	@Override
	public Integer call() throws Exception {
		getVideo();
		return null;
	}
}

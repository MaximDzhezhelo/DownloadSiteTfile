package ua.kiev.makson.work_in_site.requests;

import ua.kiev.makson.controller.controllersite.ControllerSite;

public class StartRequestGetVideo implements Runnable {
	private ControllerSite controlSite;
	private GeneralHttpClient genClient;

	public StartRequestGetVideo(ControllerSite controlSite, GeneralHttpClient genClient) {
		this.controlSite = controlSite;
		this.genClient = genClient;
	}

	public void getVideo() {
		Request request = new Request();
		request.authentication(genClient, controlSite);
		request.getVideo(genClient, controlSite);
	}

	@Override
	public void run() {
		getVideo();
	}

}

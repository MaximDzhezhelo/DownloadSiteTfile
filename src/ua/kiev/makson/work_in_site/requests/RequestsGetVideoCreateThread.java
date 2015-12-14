package ua.kiev.makson.work_in_site.requests;

import ua.kiev.makson.controller.controllersite.ControllerSite;

public class RequestsGetVideoCreateThread implements Runnable {
	private ControllerSite controlSite;
	private GeneralHttpClient genClient;

	public RequestsGetVideoCreateThread(ControllerSite controlSite, GeneralHttpClient genClient) {
		this.controlSite = controlSite;
		this.genClient = genClient;
	}

	public void getVideo() {
		RequestGetVideoStart getVideoStart = new RequestGetVideoStart(controlSite, genClient);
		getVideoStart.loopRequests();
	}

	@Override
	public void run() {
		getVideo();
	}

}

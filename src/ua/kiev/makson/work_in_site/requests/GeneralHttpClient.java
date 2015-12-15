package ua.kiev.makson.work_in_site.requests;

import ua.kiev.makson.controller.controllersite.ControllerSite;

public class GeneralHttpClient {
	private Client client;
	private Request request;
	private RequestsGetVideoCreateThread requestGetVideo;

	public GeneralHttpClient() {
		client = new Client();
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void authentication(ControllerSite controlSite) {
		request = new Request();
		request.authentication(this, controlSite);
	}

	public void getVideo(ControllerSite controlSite) {
		requestGetVideo = new RequestsGetVideoCreateThread(controlSite, this);
		new Thread(requestGetVideo).start();
	}

	public void stopVideo() {
		requestGetVideo.stopVideo();
	}

	public void stopAutentication() {
		request.stopAuthentication();
	}

}

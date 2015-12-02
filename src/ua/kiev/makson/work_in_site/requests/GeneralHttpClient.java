package ua.kiev.makson.work_in_site.requests;

import ua.kiev.makson.controller.controllersite.ControllerSite;

public class GeneralHttpClient {
	private Client client;

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
		Request request = new Request();
		request.authentication(this, controlSite);
	}

	public void getVideo(ControllerSite controlSite) {
		Request request = new Request();
		request.getVideo(this, controlSite);
	}

}

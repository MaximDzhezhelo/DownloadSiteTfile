package ua.kiev.makson.work_in_site.requests.getvideo.page;

import java.util.logging.Level;
import java.util.logging.Logger;

import ua.kiev.makson.work_in_site.requests.RequesAssistant;
import ua.kiev.makson.work_in_site.requests.getvideo.GetRequests;

public class FormingObjectVideo {
	private FormingDescription formingDescription;

	private static final Logger LOGGER = Logger.getLogger(FormingObjectVideo.class.getName());

	public VideoDescription getVideoDescription(String viewtopic, VideoDescription descriptionObject,
			RequesAssistant assistant) {
		StringBuilder sb = new StringBuilder("http://tfile.me");
		sb.append(viewtopic);
		LOGGER.log(Level.SEVERE, "Forming Object Video ");
		viewtopic = sb.toString();

		GetRequests getRequests = new GetRequests(viewtopic, assistant);
		getRequests.doGet();

		descriptionObject.setViewtopic(viewtopic);

		formingDescription = new FormingDescription();
		return formingDescription.startForming(assistant, descriptionObject);
	}
}

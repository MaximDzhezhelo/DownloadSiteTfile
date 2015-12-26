package ua.kiev.makson.work_in_site.requests.getvideo.page;

import org.apache.log4j.Logger;

import ua.kiev.makson.work_in_site.requests.RequesAssistant;
import ua.kiev.makson.work_in_site.requests.getvideo.GetRequests;

public class FormingObjectVideo {
	private FormingDescription formingDescription;

	private static final Logger LOGGER = Logger.getLogger(FormingObjectVideo.class);

	public VideoDescription getVideoDescription(String viewtopic, VideoDescription descriptionObject,
			RequesAssistant assistant) {
		StringBuilder sb = new StringBuilder("http://tfile.me");
		sb.append(viewtopic);
		LOGGER.info("Forming Object Video ");
		viewtopic = sb.toString();

		GetRequests getRequests = new GetRequests(viewtopic, assistant);
		getRequests.doGet();

		descriptionObject.setViewtopic(viewtopic);

		formingDescription = new FormingDescription();
		return formingDescription.startForming(assistant, descriptionObject);
	}
}

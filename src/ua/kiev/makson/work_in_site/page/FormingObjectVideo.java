package ua.kiev.makson.work_in_site.page;

import java.util.logging.Level;
import java.util.logging.Logger;

import ua.kiev.makson.work_in_site.requests.RequesAssistant;
import ua.kiev.makson.work_in_site.requests.get.GetRequests;

public class FormingObjectVideo {
    private static final Logger LOGGER = Logger
            .getLogger(FormingObjectVideo.class.getName());

    public void getVideoDescription(String viewtopic,
            VideoDescription description, RequesAssistant assistant) {
        StringBuilder sb = new StringBuilder("http://tfile.me");
        sb.append(viewtopic);
        LOGGER.log(Level.SEVERE, "Forming Object Video ");
        viewtopic = sb.toString();

        GetRequests getRequests = new GetRequests();
        getRequests.doGet(viewtopic, assistant);
    }
}

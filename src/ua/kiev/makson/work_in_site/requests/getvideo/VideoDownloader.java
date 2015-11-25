package ua.kiev.makson.work_in_site.requests.getvideo;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.work_in_site.page.GeneralWorkInSite;
import ua.kiev.makson.work_in_site.requests.GeneralHttpClient;
import ua.kiev.makson.work_in_site.requests.RequesAssistant;

public class VideoDownloader {
    private static final Logger LOGGER = Logger.getLogger(VideoDownloader.class
            .getName());

    public void startVideoDownload(GeneralHttpClient genClient,
            ControllerSite controlSite, Map<String, String> header) {

        String url = "http://tfile.me/forum/viewforum.php?f=4";
        LOGGER.log(Level.SEVERE, "run start Video Download");
        RequesAssistant assistant = new RequesAssistant(genClient, controlSite,
                header);

        GetRequests afterAuthentication = new GetRequests();
        afterAuthentication.doGet(url, assistant);

        GeneralWorkInSite generalWorkInSite = new GeneralWorkInSite();
        generalWorkInSite.parsingPage(assistant);
    }
}

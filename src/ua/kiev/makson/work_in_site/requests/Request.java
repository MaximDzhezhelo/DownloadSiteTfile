package ua.kiev.makson.work_in_site.requests;

import java.util.Map;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.work_in_site.requests.authentication.Authentication;
import ua.kiev.makson.work_in_site.requests.getvideo.VideoDownloader;

public class Request {
    private RequestHelper requestHelper;
    private Authentication authentication;
    private VideoDownloader downloader;
    private Map<String, String> header;

    public Request() {
        requestHelper = new RequestHelper();
        header = requestHelper.getInitialRequestHeader();
    }

    public void authentication(GeneralHttpClient genClient,
            ControllerSite controlSite) {
        if (authentication == null) {
            authentication = new Authentication();
            authentication.startAuthentication(genClient, controlSite,
                    requestHelper);
        }
    }

    public void getVideo(GeneralHttpClient genClient, ControllerSite controlSite) {
        if (downloader == null) {
            downloader = new VideoDownloader();
            downloader.startVideoDownload(genClient, controlSite, header);
        }
    }
}

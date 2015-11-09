package ua.kiev.makson.work_in_site.requests;

import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.work_in_site.requests.authentication.GetAuthentication;
import ua.kiev.makson.work_in_site.requests.authentication.PostAuthentication;
import ua.kiev.makson.work_in_site.requests.get.AfterAuthentication;

public class Request {
    private RequestHelper requestHelper;
    private static final Logger LOGGER = Logger.getLogger(Request.class
            .getName());

    private Map<String, String> header;

    public Request() {
        requestHelper = new RequestHelper();
        header = requestHelper.getInitialRequestHeader();
    }

    public void authentication(GeneralHttpClient genClient,
            ControllerSite controlSite) {

        String url = controlSite.getUrl();
        File rootDirectory = controlSite.getRootDirectory();

        GetAuthentication get = new GetAuthentication();
        get.doGet(url, header, genClient, rootDirectory);

        Map<String, String> params = requestHelper
                .getPostParamsForLogin(controlSite);
        url = "http://tfile.me/login/login.php";

        PostAuthentication post = new PostAuthentication();
        boolean registration = post.doPost(url, header, params, genClient,
                rootDirectory);

        controlSite.setRegistration(registration);
    }

    public void getVideo(GeneralHttpClient genClient, ControllerSite controlSite) {

        String url = "http://tfile.me/forum/viewforum.php?f=4";
        File rootDirectory = controlSite.getRootDirectory();
        LOGGER.log(Level.SEVERE, "run method getVideo");
        AfterAuthentication afterAuthentication = new AfterAuthentication();
        afterAuthentication.doGet(url, header, genClient, rootDirectory);

    }
}

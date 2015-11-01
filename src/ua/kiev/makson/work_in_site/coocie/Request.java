package ua.kiev.makson.work_in_site.coocie;

import java.io.File;
import java.util.Map;

import ua.kiev.makson.controller.controllersite.ControllerSite;

public class Request {
    RequestHelper requestHelper;

    public Request() {
        requestHelper = new RequestHelper();
    }

    public void authentication(GeneralHttpClient genClient,
            ControllerSite controlSite) {

        String url = controlSite.getUrl();
        File rootDirectory = controlSite.getRootDirectory();

        GetAuthentication get = new GetAuthentication();
        Map<String, String> header = requestHelper.getInitialRequestHeader();
        get.doGet(url, header, genClient, rootDirectory);

        Map<String, String> params = requestHelper
                .getPostParamsForLogin(controlSite);
        url = "http://tfile.me/login/login.php";

        PostAuthentication post = new PostAuthentication();
        boolean registration = post.doPost(url, header, params, genClient,
                rootDirectory);

        controlSite.setRegistration(registration);

    }
}

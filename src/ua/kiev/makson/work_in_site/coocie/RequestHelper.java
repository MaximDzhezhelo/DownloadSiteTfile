package ua.kiev.makson.work_in_site.coocie;

import java.util.HashMap;
import java.util.Map;

import ua.kiev.makson.controller.controllersite.ControllerSite;

public class RequestHelper {
    public Map<String, String> getInitialRequestHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("User-Agent",
                "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:41.0) Gecko/20100101 Firefox/41.0");
        header.put("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        header.put("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
        header.put("Connection", "keep-alive");
        return header;
    }

    public Map<String, String> getPostParamsForLogin(ControllerSite controlSite) {
        Map<String, String> params = new HashMap<>();
        params.put("username", controlSite.getLogin());
        params.put("password", controlSite.getPassword());
        params.put("redirect", "");
        params.put("login", "%C2%F5%EE%E4");
        return params;
    }

}

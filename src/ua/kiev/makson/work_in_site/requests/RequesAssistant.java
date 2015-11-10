package ua.kiev.makson.work_in_site.requests;

import java.util.Map;

import ua.kiev.makson.controller.controllersite.ControllerSite;

public class RequesAssistant {
    private GeneralHttpClient genClient;
    private ControllerSite controlSite;
    private Map<String, String> header;

    public RequesAssistant(GeneralHttpClient genClient,
            ControllerSite controlSite, Map<String, String> header) {
        this.genClient = genClient;
        this.controlSite = controlSite;
        this.header = header;
    }

    public GeneralHttpClient getGenClient() {
        return genClient;
    }

    public void setGenClient(GeneralHttpClient genClient) {
        this.genClient = genClient;
    }

    public ControllerSite getControlSite() {
        return controlSite;
    }

    public void setControlSite(ControllerSite controlSite) {
        this.controlSite = controlSite;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

}

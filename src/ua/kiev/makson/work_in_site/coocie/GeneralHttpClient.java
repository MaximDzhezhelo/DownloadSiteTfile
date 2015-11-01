package ua.kiev.makson.work_in_site.coocie;

import java.io.File;

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

    // public static void main(String[] args) {
    // GeneralHttpClient genClient = new GeneralHttpClient();
    // ControllerSite control = new ControllerSite("http://tfile.me/",
    // "Mq1aximer", "asdasdasd", new File("wqertyjnbvdsfg"));
    // genClient.authentication(control);
    //
    // }

}

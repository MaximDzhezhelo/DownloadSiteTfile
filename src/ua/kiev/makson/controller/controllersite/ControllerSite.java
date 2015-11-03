package ua.kiev.makson.controller.controllersite;

import java.io.File;

import ua.kiev.makson.work_in_site.requests.GeneralHttpClient;

public class ControllerSite {
    private String urlString;
    private String login;
    private String password;
    private File rootDirectory;
    private boolean registration;
    private GeneralHttpClient genClient;

    public ControllerSite(String url, String login, String password,
            File rootDirectory) {
        this.urlString = url;
        this.login = login;
        this.password = password;
        this.rootDirectory = rootDirectory;
        genClient = new GeneralHttpClient();
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
    }

    public String getUrl() {
        return urlString;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public File getRootDirectory() {
        return rootDirectory;
    }

    public void setUrlLogPass(String url, String login, String password,
            File rootDirectory) {
        this.urlString = url;
        this.login = login;
        this.password = password;
        this.rootDirectory = rootDirectory;
    }

    public void shoow() {
        System.out.println(urlString);
        System.out.println(login);
        System.out.println(password);
    }

    public void loginStart() {
        genClient.authentication(this);
    }

    public void goInTheSite() {
        genClient.getVideo(this);
    }

    public boolean setColorRegistration() {
        return registration;
    }
}

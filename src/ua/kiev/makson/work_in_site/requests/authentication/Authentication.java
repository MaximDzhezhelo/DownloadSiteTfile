package ua.kiev.makson.work_in_site.requests.authentication;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.timer.RandomTime;
import ua.kiev.makson.work_in_site.requests.GeneralHttpClient;
import ua.kiev.makson.work_in_site.requests.RequesAssistant;
import ua.kiev.makson.work_in_site.requests.RequestHelper;

public class Authentication {
    private ScheduledExecutorService executor;
    private RandomTime randomTime;
    private GetAuthentication get;
    private PostAuthentication post;
    private ScheduledFuture<Integer> future;
    private Map<String, String> params;
    private int time;
    private int statusLine;
    // static int x = 0;

    private static final Logger LOGGER = Logger.getLogger(Authentication.class
            .getName());

    public Authentication() {
        executor = Executors.newScheduledThreadPool(1);
        randomTime = new RandomTime();
    }

    public int getStatusLine() {
        return statusLine;
    }

    public RandomTime getRandomTime() {
        return randomTime;
    }

    public void startAuthentication(GeneralHttpClient genClient,
            ControllerSite controlSite, RequestHelper requestHelper) {
        String url = controlSite.getUrl();
        Map<String, String> header = requestHelper.getInitialRequestHeader();
        RequesAssistant assistant = new RequesAssistant(genClient, controlSite,
                header);

        get = new GetAuthentication(url, assistant);

        try {
            statusLine = callGet();

            // statusLine = x;
            // if (x == 2) {
            // LOGGER.log(Level.SEVERE, "x == 2 ");
            // x = 200;
            // }

            params = requestHelper.getPostParamsForLogin(controlSite);
            url = "http://tfile.me/login/login.php";
            post = new PostAuthentication(url, header, params, genClient);
            statusLine = callPost();
            controlSite.setRegistration(statusLine == 302);

        } catch (InterruptedException | ExecutionException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        } finally {
            LOGGER.log(Level.SEVERE, "executor shutdown");
            executor.shutdownNow();
        }

    }

    private int callGet() throws InterruptedException, ExecutionException {
        // x++;
        LOGGER.log(Level.SEVERE, "callGet ");
        time = randomTime.getRandomTime();
        LOGGER.log(Level.SEVERE, "randomTime " + time);
        future = executor.schedule(get, time, TimeUnit.SECONDS);
        statusLine = future.get();
        if (statusLine == 200) {
            return statusLine;
        } else {
            LOGGER.log(Level.SEVERE, "statusLine !==200 run again GET ");
            callGet();
        }
        return statusLine;
    }

    private int callPost() throws InterruptedException, ExecutionException {
        LOGGER.log(Level.SEVERE, "callPost ");
        future = executor.schedule(post, 3, TimeUnit.SECONDS);
        statusLine = future.get();
        if (statusLine == 302) {
            return statusLine;
        } else {
            LOGGER.log(Level.SEVERE, "statusLine !==302 run again Post ");
            callPost();
        }
        return statusLine;
    }

}

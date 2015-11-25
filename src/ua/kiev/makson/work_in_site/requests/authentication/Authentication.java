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
    private ScheduledFuture<Integer> future;
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

            if (statusLine == 200) {
                Map<String, String> params = requestHelper
                        .getPostParamsForLogin(controlSite);
                url = "http://tfile.me/login/login.php";

                PostAuthentication post = new PostAuthentication(url, header,
                        params, genClient);
                future = executor.schedule(post, time, TimeUnit.SECONDS);
                statusLine = future.get();
                controlSite.setRegistration(statusLine == 302);
                if (statusLine != 302) {
                    callGet();
                }
            } else {
                callGet();
            }
            LOGGER.log(Level.SEVERE, "executor shutdown");
            executor.shutdown();
        } catch (InterruptedException | ExecutionException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        } finally {
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

}

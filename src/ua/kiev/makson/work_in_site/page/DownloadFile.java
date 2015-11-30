package ua.kiev.makson.work_in_site.page;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import ua.kiev.makson.work_in_site.requests.RequesAssistant;

public class DownloadFile {

    private static final Logger LOGGER = Logger.getLogger(DownloadFile.class
            .getName());

    public VideoDescription startDownload(String downloadUrl,
                                          VideoDescription description, RequesAssistant assistant) {
        LOGGER.log(Level.SEVERE, "startDownload");
        ExecutorService exService = Executors.newCachedThreadPool();
        CallableDownload callableDownload = new CallableDownload(downloadUrl,
                description, assistant);
        Future<VideoDescription> answerDownload = exService
                .submit(callableDownload);
        try {
            description = answerDownload.get();
            exService.shutdown();
            LOGGER.log(Level.SEVERE, "cancelDownload");
        } catch (InterruptedException | ExecutionException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        return description;
    }
}

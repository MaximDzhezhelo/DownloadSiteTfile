package ua.kiev.makson.work_in_site.page;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.work_in_site.FileWrite;
import ua.kiev.makson.work_in_site.requests.RequesAssistant;
import ua.kiev.makson.work_in_site.requests.get.GetDownlodRequests;

public class CallableDownload implements Callable<VideoDescription> {
    private String downloadUrl;
    private VideoDescription description;
    private RequesAssistant assistant;
    private static final Logger LOGGER = Logger
            .getLogger(CallableDownload.class.getName());

    public CallableDownload(String downloadUrl, VideoDescription description,
            RequesAssistant assistant) {
        this.assistant = assistant;
        this.description = description;
        this.downloadUrl = String.format("http://tfile.me%s", downloadUrl);
    }

    @Override
    public VideoDescription call() throws Exception {
        LOGGER.log(Level.SEVERE, "call method (download torrent)");
        ControllerSite controlSite = assistant.getControlSite();
        File rootDirectory = controlSite.getRootDirectory();
        String nameFolder = description.getName();
        String nameFile = String.format("%s.torrent", nameFolder);
        String namedescr = String.format("%s.txt", nameFolder);
        String nameIMG = String.format("%s.jpg", nameFolder);

        File fileFolder = new File(rootDirectory, nameFolder);

        if (!fileFolder.exists()) {
            fileFolder.mkdir();
        }

        GetDownlodRequests requests = new GetDownlodRequests();

        File fileDownload = new File(fileFolder, nameFile);

        if (!fileDownload.exists()) {
            fileDownload.createNewFile();
        }
        requests.doGet(downloadUrl, assistant, fileDownload);
        
        File fileIMG = new File(fileFolder, nameIMG);
        String imgUrl = description.getJpg();

        if (!fileIMG.exists()) {
            fileIMG.createNewFile();
        }

        requests.doGet(imgUrl, assistant, fileIMG);

        String getPath = fileDownload.getAbsolutePath();
        description.setDownloadUrl(getPath);

        FileWrite fileWrite = new FileWrite();
        fileWrite.writeInFile(description.toString(), fileFolder, "UTF-8",
                namedescr);

        return description;
    }

}

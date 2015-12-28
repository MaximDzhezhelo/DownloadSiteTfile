package ua.kiev.makson.work_in_site.requests.getvideo.page;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.timer.RandomTime;
import ua.kiev.makson.work_in_site.FileWrite;
import ua.kiev.makson.work_in_site.requests.RequesAssistant;
import ua.kiev.makson.work_in_site.requests.getvideo.GetDownlodRequests;

public class DownloadFile {

	private String downloadUrl;
	private VideoDescription description;
	private RequesAssistant assistant;
	private ScheduledExecutorService executor;
	private ScheduledFuture<Integer> future;
	private RandomTime randomTime;
	private int time;
	private static final Logger LOGGER = Logger.getLogger(DownloadFile.class);

	public DownloadFile(String downloadUrl, VideoDescription description, RequesAssistant assistant) {
		this.assistant = assistant;
		this.description = description;
		this.downloadUrl = String.format("http://tfile.me%s", downloadUrl);
		executor = Executors.newScheduledThreadPool(1);
		randomTime = new RandomTime();
	}

	public VideoDescription startDownload() {
		LOGGER.info("startDownload (download torrent)");
		ControllerSite controlSite = assistant.getControlSite();
		File rootDirectory = controlSite.getRootDirectory();
		String nameFolder = description.getName();
		String nameFile = String.format("%s.torrent", nameFolder);
		String namedescr = String.format("%s.txt", nameFolder);
		String nameIMG = String.format("%s.jpg", nameFolder);

		File torrentFolder = new File(rootDirectory, "Torrents");
		isFileFolderExists(torrentFolder);

		File fileFolder = new File(rootDirectory, nameFolder);
		isFileFolderExists(fileFolder);

		File fileDownload = new File(torrentFolder, nameFile);
		isFileExists(fileDownload);
		downloadFile(downloadUrl, assistant, fileDownload);

		File fileIMG = new File(fileFolder, nameIMG);
		String imgUrl = description.getJpg();
		isFileExists(fileIMG);
		downloadFile(imgUrl, assistant, fileIMG);

		String getPath = fileDownload.getAbsolutePath();
		description.setDownloadTorrent(getPath);

		FileWrite fileWrite = new FileWrite();
		fileWrite.writeInFile(description.toString(), fileFolder, "UTF-8", namedescr);
		executor.shutdown();
		return description;
	}

	private void isFileExists(File file) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ex) {
				if (!executor.isShutdown()) {
					executor.shutdownNow();
				}
				LOGGER.error(ex.getMessage());
			}
		}
	}

	private void isFileFolderExists(File file) {
		if (!file.exists()) {
			file.mkdir();
		}
	}

	private void downloadFile(String url, RequesAssistant assistant, File file) {
		GetDownlodRequests requests = new GetDownlodRequests(url, assistant, file);
		LOGGER.info("downloadFile ");
		time = randomTime.getRandomDownload();
		LOGGER.info("randomTime downloadFile " + time);
		future = executor.schedule(requests, time, TimeUnit.SECONDS);
		try {
			future.get();
		} catch (InterruptedException | ExecutionException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	public void stopDownload() {
		if (!executor.isShutdown()) {
			LOGGER.info("executor DownloadFile shutdown");
			executor.shutdown();
		}
	}
}

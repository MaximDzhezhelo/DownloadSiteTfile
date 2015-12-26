package ua.kiev.makson.work_in_site.requests.getvideo.page;

import java.io.File;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.controller.controllersite.ControllerWorkInSitePanel;
import ua.kiev.makson.sql.JavaSQL;
import ua.kiev.makson.torrent.Executor;
import ua.kiev.makson.work_in_site.FileRead;
import ua.kiev.makson.work_in_site.requests.RequesAssistant;
import ua.kiev.makson.work_in_site.requests.getvideo.page.updateworkinsitepanel.UpdateWorkInSitePanel;
import ua.kiev.makson.work_in_site.requests.getvideo.page.updateworkinsitepanel.UpdateWorkInSitePanelCountField;
import ua.kiev.makson.work_in_site.requests.getvideo.page.updateworkinsitepanel.UpdateWorkInSitePanelTableModel;

public class WorkWithPage {
	private FormingObjectVideo objectVideo;
	private DownloadFile download;
	private boolean stop;
	private Executor executor;
	private static final Logger LOGGER = Logger.getLogger(WorkWithPage.class);

	public void parsingPage(JavaSQL javaSQL, RequesAssistant assistant) {
		ControllerSite controlSite = assistant.getControlSite();
		String charset = controlSite.getCharset();
		String fileName = controlSite.getDefaultReadName();
		File rootDirectory = controlSite.getRootDirectory();
		File file = new File(rootDirectory, fileName);
		if (file.exists()) {
			FileRead fileRead = new FileRead(charset);
			String page = fileRead.readFromRootDirectory(file);

			Document doc = Jsoup.parse(page);
			VideoDescription description = new VideoDescription();

			Element body = doc.body();
			Elements div = body.select("tr.tor");
			for (int i = 0; i < div.size() && !stop; i++) {
				Element elementDiv = div.get(i);
				Elements clasI = elementDiv.getElementsByClass("i");

				Elements tagB = clasI.select("b");

				String conditionTitle = tagB.attr("title");

				if (conditionTitle.equals("Проверенный релиз") || conditionTitle.equals("Временная раздача")) {
					Elements classT = elementDiv.getElementsByClass("t");
					String nameFile = classT.text();

					int x = nameFile.indexOf('/');
					String nameOfFile = nameFile.substring(0, x).trim();

					boolean thereIs = javaSQL.checkVideoByName(nameOfFile);
					if (!thereIs) {
						description.setName(nameOfFile);

						Elements links = classT.select("a[href]");
						for (Element link : links) {
							String viewtopic = link.attr("href");

							description = setViewtopic(viewtopic, description, assistant);
						}

						Elements classDL = elementDiv.getElementsByClass("dl");
						Elements linksDL = classDL.select("a[href]");
						for (Element link : linksDL) {
							String downloadUrl = link.attr("href");
							description = setDownloadUrl(downloadUrl, description, assistant);
						}
						javaSQL.writeData(description);
						updatePanel(controlSite, description.getName());
						downloadTorrent(controlSite, description);
					}
				}
			}
		} else {
			LOGGER.info("File is not exists");
		}
	}

	private VideoDescription setViewtopic(String viewtopic, VideoDescription description, RequesAssistant assistant) {
		objectVideo = new FormingObjectVideo();
		return objectVideo.getVideoDescription(viewtopic, description, assistant);
	}

	private VideoDescription setDownloadUrl(String downloadUrl, VideoDescription description,
			RequesAssistant assistant) {

		download = new DownloadFile(downloadUrl, description, assistant);
		return download.startDownload();
	}

	public void stopDownload() {
		stop = true;
		download.stopDownload();
		executor.executorStopDownload();
	}

	private void updatePanel(ControllerSite controlSite, String nameOfFile) {
		UpdateWorkInSitePanel update = new UpdateWorkInSitePanelCountField(controlSite);
		new Thread(update).start();
		UpdateWorkInSitePanel update2 = new UpdateWorkInSitePanelTableModel(controlSite, nameOfFile);
		new Thread(update2).start();
	}

	private void downloadTorrent(ControllerSite controllerSite, VideoDescription description) {
		ControllerWorkInSitePanel controllerWorkInSitePanel = controllerSite.getControllerWorkInSitePanel();
		if (executor == null) {
			executor = controllerWorkInSitePanel.getExecutor();
		}
		executor.executorStartDownload(description);
	}

}

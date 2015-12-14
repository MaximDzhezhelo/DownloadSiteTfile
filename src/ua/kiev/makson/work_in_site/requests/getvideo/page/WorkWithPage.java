package ua.kiev.makson.work_in_site.requests.getvideo.page;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.sql.JavaSQL;
import ua.kiev.makson.work_in_site.FileRead;
import ua.kiev.makson.work_in_site.requests.RequesAssistant;

public class WorkWithPage {
	private FormingObjectVideo objectVideo;
	private DownloadFile download;
	private boolean stop;
	private static final Logger LOGGER = Logger.getLogger(WorkWithPage.class.getName());

	public void parsingPage(JavaSQL javaSQL, RequesAssistant assistant) {
		ControllerSite controlSite = assistant.getControlSite();
		String charset = controlSite.getCharset();
		String fileName = controlSite.getDefaultReadName();
		File rootDirectory = controlSite.getRootDirectory();
		File file = new File(rootDirectory, fileName);
		if (file.exists()) {
			LOGGER.log(Level.SEVERE, "File exists");
			FileRead fileRead = new FileRead(charset);
			String page = fileRead.readFromRootDirectory(file);

			Document doc = Jsoup.parse(page);
			VideoDescription description = new VideoDescription();

			String title = doc.title();
			System.out.println(title);

			Element body = doc.body();
			Elements div = body.select("tr.tor");
			for (int i = 0; i < div.size() && !stop; i++) {
				// for (Element elementDiv : div) {
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
						LOGGER.log(Level.SEVERE, "nameOfFile there is");

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
						javaSQL.writeData(nameOfFile, description.toString());
						updatePanel(controlSite);
					}
				}
			}
		} else {
			LOGGER.log(Level.SEVERE, "File is not exists");
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
	}

	private void updatePanel(ControllerSite controlSite) {
		UpdateWorkInSitePanel update = new UpdateWorkInSitePanel(controlSite);
		new Thread(update).start();
	}

}

package ua.kiev.makson.work_in_site.page;

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
    private static final Logger LOGGER = Logger.getLogger(WorkWithPage.class
            .getName());

    public void parsingPage(JavaSQL javaSQL, RequesAssistant assistant) {
        ControllerSite controlSite = assistant.getControlSite();
        String charset = controlSite.getCharset();
        File file = new File("site.html");
        if (file.exists()) {
            FileRead fileRead = new FileRead(charset);
            String page = fileRead.readFromRootDirectory(file);

            Document doc = Jsoup.parse(page);
            VideoDescription description = new VideoDescription();
            String title = doc.title();
            System.out.println(title);

            Element body = doc.body();
            Elements div = body.select("tr.tor");
            for (Element elementDiv : div) {
                Elements clasI = elementDiv.getElementsByClass("i");

                Elements tagB = clasI.select("b");

                String conditionTitle = tagB.attr("title");

                if (conditionTitle.equals("Проверенный релиз")
                        || conditionTitle.equals("Временная раздача")) {
                    Elements classT = elementDiv.getElementsByClass("t");
                    String nameFile = classT.text();

                    int x = nameFile.indexOf('/');
                    String nameOfFile = nameFile.substring(0, x).trim();

                    boolean thereIs = javaSQL.checkVideoByName(nameOfFile);
                    if (!thereIs) {
                        description.setName(nameOfFile);
                        LOGGER.log(Level.SEVERE, "nameOfFile there is");
                        String category = nameFile.substring(x + 1,
                                nameFile.length()).trim();

                        // video.setName(nameOfFile);
                        // video.setCategory(category);

                        Elements links = classT.select("a[href]");
                        for (Element link : links) {
                            String viewtopic = link.attr("href");
                            FormingObjectVideo objectVideo = new FormingObjectVideo();
                            objectVideo.getVideoDescription(viewtopic,
                                    description, assistant);
                            // video.setViewtopic(viewtopic);
                        }

                        Elements classDL = elementDiv.getElementsByClass("dl");
                        Elements linksDL = classDL.select("a[href]");
                        for (Element link : linksDL) {
                            String downloadUrl = link.attr("href");
                            // video.setDownloadUrl(downloadUrl);
                        }
                        // javaSQL.writeData(nameOfFile, video.toString());
                    }
                }
            }
        } else {
            LOGGER.log(Level.SEVERE, "File is not exists");
        }

    }
}

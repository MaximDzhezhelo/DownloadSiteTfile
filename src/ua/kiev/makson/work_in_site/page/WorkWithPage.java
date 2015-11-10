package ua.kiev.makson.work_in_site.page;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ua.kiev.makson.sql.JavaSQL;
import ua.kiev.makson.work_in_site.FileRead;

public class WorkWithPage {
    private static final Logger LOGGER = Logger.getLogger(WorkWithPage.class
            .getName());

    public static void main(String[] args) {
        JavaSQL sql = new JavaSQL();
        File db = new File("collection.db");
        if (!db.exists()) {
            sql.createSQL();
        }

        File file = new File("site.html");
        if (file.exists()) {
            String charset = "Windows-1251";
            FileRead fileRead = new FileRead(charset);
            String page = fileRead.readFromRootDirectory(file);

            Document doc = Jsoup.parse(page);
            String title = doc.title();
            System.out.println(title);

            Element body = doc.body();
            Elements div = body.select("tr.tor");
            for (Element elementDiv : div) {
                Elements clasI = elementDiv.getElementsByClass("i");

                Elements tagB = clasI.select("b");

                String conditionTitle = tagB.attr("title");

                Video video = new Video();
                if (conditionTitle.equals("Проверенный релиз")
                        || conditionTitle.equals("Временная раздача")) {
                    Elements classT = elementDiv.getElementsByClass("t");
                    String nameFile = classT.text();

                    int x = nameFile.indexOf('/');
                    String nameOfFile = nameFile.substring(0, x).trim();
                    String category = nameFile.substring(x + 1,
                            nameFile.length()).trim();
                    video.setName(nameOfFile);
                    video.setCategory(category);

                    Elements links = classT.select("a[href]");
                    for (Element link : links) {
                        String viewtopic = link.attr("href");
                        video.setViewtopic(viewtopic);
                    }

                    Elements classDL = elementDiv.getElementsByClass("dl");
                    Elements linksDL = classDL.select("a[href]");
                    for (Element link : linksDL) {
                        String downloadUrl = link.attr("href");
                        video.setDownloadUrl(downloadUrl);
                    }
                    sql.writeData(nameOfFile, video.toString());
                }
            }
            sql.showAll();
        } else {
            LOGGER.log(Level.SEVERE, "File is not exists");
        }
    }
}

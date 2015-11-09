package ua.kiev.makson.work_in_site.page;

import java.io.File;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ua.kiev.makson.sql.JavaSQL;
import ua.kiev.makson.work_in_site.FileRead;

public class WorkWithPage {

    public static void main(String[] args) {
        JavaSQL sql = new JavaSQL();
        File db = new File("collection.db");
        if (!db.exists()) {
            sql.createSQL();
        }

        File file = new File("site.html");
        HashMap<String, Video> map = new HashMap<>();
        if (file.exists()) {
            String charset = "Windows-1251";
            FileRead fileRead = new FileRead(charset);
            String page = fileRead.readFromRootDirectory(file);

            Document doc = Jsoup.parse(page);
            String title = doc.title();
            System.out.println(title);

            Element body = doc.body();
            Elements div = body.select("tr.tor");
            int i = 0;
            for (Element elementDiv : div) {
                Elements clasI = elementDiv.getElementsByClass("i");
                Elements tagB = clasI.select("b");
                String conditionTitle = tagB.attr("title");
                System.out.println(conditionTitle);
                Video video = new Video();
                if (conditionTitle.equals("Проверенный релиз")
                        || conditionTitle.equals("Временная раздача")) {
                    i++;
                    Elements classT = elementDiv.getElementsByClass("t");
                    String nameFile = classT.text();

                    int x = nameFile.indexOf('/');
                    String nameOfFile = nameFile.substring(0, x);
                    String category = nameFile.substring(x + 1,
                            nameFile.length());
                    video.setName(nameOfFile);
                    video.setCategory(category);

                    System.out.println(nameFile);

                    Elements links = classT.select("a[href]");
                    for (Element link : links) {
                        String viewtopic = link.attr("href");
                        video.setViewtopic(viewtopic);
                        System.out.println(viewtopic);
                    }

                    Elements classDL = elementDiv.getElementsByClass("dl");
                    Elements linksDL = classDL.select("a[href]");
                    for (Element link : linksDL) {
                        String downloadUrl = link.attr("href");
                        video.setDownloadUrl(downloadUrl);
                        System.out.println(downloadUrl);
                    }
                    sql.writeData(nameOfFile, null, null, category);
                    map.put(nameOfFile, video);
                }
            }
            System.out.println(i);

            sql.showAll();
        } else {
            System.out.println("File is not exists");
        }
    }
}

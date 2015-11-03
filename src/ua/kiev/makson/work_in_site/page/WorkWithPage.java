package ua.kiev.makson.work_in_site.page;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ua.kiev.makson.work_in_site.FileRead;

public class WorkWithPage {

    public static void main(String[] args) {
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
            int i = 0;
            for (Element elementDiv : div) {
                Elements clasI = elementDiv.getElementsByClass("i");
                Elements tagB = clasI.select("b");
                String conditionTitle = tagB.attr("title");
                System.out.println(conditionTitle);
                if (conditionTitle.equals("Проверенный релиз")
                        || conditionTitle.equals("Временная раздача")) {
                    i++;
                    Elements classT = elementDiv.getElementsByClass("t");
                    String nameFile = classT.text();
                    System.out.println(nameFile);
                    
                    Elements links = classT.select("a[href]");
                    for (Element link : links) {
                        String url = link.attr("href");
                        System.out.println(url);
                    }

                    Elements classDL = elementDiv.getElementsByClass("dl");
                    Elements linksDL = classDL.select("a[href]");
                    for (Element link : linksDL) {
                        String downloadUrl = link.attr("href");
                        System.out.println(downloadUrl);
                    }
                }
            }
            System.out.println(i);
        }
    }
}

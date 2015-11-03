package ua.kiev.makson.work_in_site.requests.get;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import ua.kiev.makson.work_in_site.FileRead;

public class WorkWithPage {

    public static void main(String[] args) {
        File file = new File("site.html");
        if (file.exists()) {
            String charset = "Windows-1251";
            FileRead fileRead = new FileRead(charset);
            String page = fileRead.readFromRootDirectory(file);
            System.out.println(page);

            Document doc = Jsoup.parse(page);
            String title = doc.title();
            System.out.println(title);
        }

    }
}

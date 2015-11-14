package ua.kiev.makson.work_in_site.page;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ua.kiev.makson.work_in_site.FileRead;

public class CreateString {

    public static void main(String[] args) {
        FileRead fileRead = new FileRead("windows-1251");
        File file = new File("site.html");
        if (file.exists()) {
            String page = fileRead.readFromRootDirectory(file);

            Document doc = Jsoup.parse(page);

            CreateString create = new CreateString();
            Element body = doc.body();

            System.out.println(create.getIMG(body));
            System.out.println(create.getDescription(body));

        }
    }

    public String getIMG(Element body) {
        Element img = body.getElementById("postImgAligned");
        String imgURL = img.attr("src");
        return imgURL;
    }

    public String getDescription(Element body) {
        StringBuilder sb = new StringBuilder();
        Elements el = body.getElementsByClass("field");
        for (Element element : el) {
            String box = element.ownText();
            sb.append(box);
            String descr = element.nextSibling().toString();
            if (box.equals("Описание:")) {
                descr = getDescrLongText(body);
            }
            descr = String.format("%s%n", descr);
            sb.append(descr);
        }
        return sb.toString();
    }

    private String getDescrLongText(Element body) {
        Elements opisSPAN = body.select("span[style=color: darkgreen]");
        return opisSPAN.text();
    }
}

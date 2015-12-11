package ua.kiev.makson.work_in_site.requests.getvideo.page;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.work_in_site.FileRead;
import ua.kiev.makson.work_in_site.requests.RequesAssistant;

public class FormingDescription {

    public VideoDescription startForming(RequesAssistant assistant,
            VideoDescription descriptionObject) {
        ControllerSite controlSite = assistant.getControlSite();
        String charset = controlSite.getCharset();
        String fileName = controlSite.getDefaultReadName();
        FileRead fileRead = new FileRead(charset);
        File rootDirectory = controlSite.getRootDirectory();
        File file = new File(rootDirectory, fileName);

        String page = fileRead.readFromRootDirectory(file);

        Document doc = Jsoup.parse(page);
        Element body = doc.body();
        String img = getIMG(body);
        String description = getDescription(body);
        String url = getURL(body);
        descriptionObject.setImg(img);
        descriptionObject.setDescription(description);
        descriptionObject.setUrl(url);

        return descriptionObject;
    }

    private String getIMG(Element body) {
        Element img = body.getElementById("postImgAligned");
        return img.attr("src");
    }

    private String getURL(Element body) {
        Elements url = body.getElementsByClass("dlLink");
        return url.attr("href");
    }

    private String getDescription(Element body) {
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

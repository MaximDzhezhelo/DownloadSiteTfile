package ua.kiev.makson.work_in_site.requests.get;

import java.io.File;

import javax.swing.text.Document;

import ua.kiev.makson.work_in_site.FileRead;

public class WorkWithPage {

    public static void main(String[] args) {
        File file = new File("site.html");
        if (file.exists()) {
            FileRead fileRead = new FileRead();
            String page = fileRead.readFromRootDirectory(file);
            System.out.println(page);
            
            Document doc = 
        }

    }
}

package ua.kiev.makson.controller.regularexpressionpattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ParsingHTML {
    private HtmlLinkExtractor link;
    private ArrayList<String> href;

    public ParsingHTML() {
        link = new HtmlLinkExtractor();
    }

    public ArrayList<String> parseFile(File file) {
        if (file == null) {
            throw new NullPointerException("Look well what file you entered");
        } else if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file)))) {
                String text = null;
                while ((text = reader.readLine()) != null) {
                    System.out.println(text);
                    href = link.grabHTMLTagLinks(text);
                    href = parseHref(href);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new NullPointerException("File not found");
        }
        return href;
    }

    private ArrayList<String> parseHref(ArrayList<String> href) {
        for (String string : href) {
            if (string != null) {
                href.add(string);
            }
        }
        return href;
    }

    public static void main(String[] args) {
        ParsingHTML parse = new ParsingHTML();
        File file = new File("testFile");
        ArrayList<String> href1 = parse.parseFile(file);
        System.out.println(href1);
    }
}

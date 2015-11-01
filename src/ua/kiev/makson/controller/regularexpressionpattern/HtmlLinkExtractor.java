package ua.kiev.makson.controller.regularexpressionpattern;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlLinkExtractor {
    private Pattern patternTag;
    private Matcher matcherTag;
    private Pattern patternLink;
    private Matcher matcherLink;

    private static final String HTML_A_TAG_PATTERN = "<a\\s([^>]+)>(.+?)</a>";
    private static final String HTML_A_HREF_TAG_PATTERN = "([^'\"=>]+)";

    public HtmlLinkExtractor() {
        patternTag = Pattern.compile(HTML_A_TAG_PATTERN);
        patternLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
    }

    public ArrayList<String> grabHTMLTagLinks(String html) {
        ArrayList<String> result = new ArrayList<>();
        matcherTag = patternTag.matcher(html);
        String href;
        if (matcherTag.find()) {
            href = matcherTag.group(1);
            href = grabHTMLLinks(href);
            result.add(href);
        }
        return result;
    }

    private String grabHTMLLinks(String href) {
        String linkText = null;
        matcherLink = patternLink.matcher(href);
        while (matcherLink.find()) {
            linkText = matcherLink.group();
        }
        return linkText;
    }
}

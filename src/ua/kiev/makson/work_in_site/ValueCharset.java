package ua.kiev.makson.work_in_site;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;

public class ValueCharset {

    public String getTheValueOfCharset(CloseableHttpResponse response) {
        Header[] headers = response.getHeaders("Content-Type");
        for (Header header : headers) {
            HeaderElement[] headerElements = header.getElements();
            for (HeaderElement headerElement : headerElements) {
                NameValuePair nameValuePair = headerElement
                        .getParameterByName("charset");
                return nameValuePair.getValue();
            }
        }
        return null;
    }

}

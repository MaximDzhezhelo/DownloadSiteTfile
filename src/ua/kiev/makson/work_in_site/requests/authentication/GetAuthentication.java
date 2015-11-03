package ua.kiev.makson.work_in_site.requests.authentication;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import ua.kiev.makson.work_in_site.FileRead;
import ua.kiev.makson.work_in_site.FileWrite;
import ua.kiev.makson.work_in_site.ValueCharset;
import ua.kiev.makson.work_in_site.requests.Client;
import ua.kiev.makson.work_in_site.requests.GeneralHttpClient;

public class GetAuthentication {
    private int statusLine;
    private static final Logger LOGGER = Logger
            .getLogger(GetAuthentication.class.getName());

    public int getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(int statusLine) {
        this.statusLine = statusLine;
    }

    public void doGet(String url, Map<String, String> header,
            GeneralHttpClient genClient, File rootDirectory) {
        Client client = genClient.getClient();
        List<Cookie> cookies = client.getCookies();
        BasicCookieStore cookieStore = client.getCookieStore();
        CloseableHttpClient httpClient = client.getHttpClient();
        boolean debug = client.isDebug();
        if (cookies.isEmpty() && debug) {
            // LOGGER.log(Level.SEVERE,
            // "Cookies NOT added by previous request. Skiping add cookie...");
        } else {
            StringBuilder builder = new StringBuilder();
            for (Cookie cookie : cookies) {
                builder.append(cookie.getName()).append('=')
                        .append(cookie.getValue()).append(';');
            }
            header.put("Cookie", builder.toString());
        }
        HttpGet httpGet = new HttpGet(url);
        for (String key : header.keySet()) {
            httpGet.addHeader(key, header.get(key));
        }

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            ValueCharset valueCharset = new ValueCharset();
            String charset = valueCharset.getTheValueOfCharset(response);

            if (debug) {
                statusLine = response.getStatusLine().getStatusCode();
                LOGGER.log(Level.SEVERE, "statusLine " + statusLine, statusLine);
            }

            HttpEntity entity = response.getEntity();

            FileRead fileRead = new FileRead();
            String docPage = fileRead.readFromEntity(entity, charset);

            FileWrite fileWrite = new FileWrite();
            fileWrite.writeInFile(docPage, rootDirectory, charset);

            EntityUtils.consume(entity);
            cookies = cookieStore.getCookies();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                    genClient.setClient(client);
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, ex.getMessage());
                }
            }
        }
    }
}
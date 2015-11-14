package ua.kiev.makson.timer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
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

public class GetTask extends TimerTask {
    private int statusLine;
    private static final Logger LOGGER = Logger.getLogger(GetTask.class
            .getName());

    private String url;
    private Map<String, String> header;
    private GeneralHttpClient genClient;
    private File rootDirectory;
    private Client client;
    private List<Cookie> cookies;
    private BasicCookieStore cookieStore;
    private CloseableHttpClient httpClient;
    Timer timer = new Timer();

    public GetTask(String url, Map<String, String> header,
            GeneralHttpClient genClient, File rootDirectory) {
        this.genClient = genClient;
        this.header = header;
        this.rootDirectory = rootDirectory;
        this.url = url;
        client = genClient.getClient();
        cookies = client.getCookies();
        cookieStore = client.getCookieStore();
        httpClient = client.getHttpClient();
    }

    public int getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(int statusLine) {
        this.statusLine = statusLine;
    }

    public void doGet() {
        LOGGER.log(Level.SEVERE, "Start get");
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
            LOGGER.log(Level.SEVERE, "response ");
            ValueCharset valueCharset = new ValueCharset();
            String charset = valueCharset.getTheValueOfCharset(response);
            client.setCharset(charset);

            if (debug) {
                statusLine = response.getStatusLine().getStatusCode();
                LOGGER.log(Level.SEVERE, "statusLine " + statusLine, statusLine);
            }
            HttpEntity entity = response.getEntity();
            if (!(statusLine == 200)) {

                FileRead fileRead = new FileRead(charset);
                String docPage = fileRead.readFromEntity(entity);

                FileWrite fileWrite = new FileWrite();
//                fileWrite.writeInFile(docPage, rootDirectory, charset);
                LOGGER.log(Level.SEVERE, "file write ");

            }
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

    @Override
    public void run() {
        doGet();
        System.out.println("task cansel");
        cancel();
    }
}

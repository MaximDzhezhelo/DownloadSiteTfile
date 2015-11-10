package ua.kiev.makson.work_in_site;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import ua.kiev.makson.work_in_site.requests.Client;
import ua.kiev.makson.work_in_site.requests.GeneralHttpClient;

public class AnalysisEntity {
    private static final Logger LOGGER = Logger.getLogger(AnalysisEntity.class
            .getName());

    public void getDataEntity(CloseableHttpResponse response,
            GeneralHttpClient genClient, File rootDirectory) {
        LOGGER.log(Level.SEVERE, "run Analysis Entity");
        HttpEntity entity = response.getEntity();

        ValueCharset valueCharset = new ValueCharset();
        String charset = valueCharset.getTheValueOfCharset(response);
        Client client = genClient.getClient();
        client.setCharset(charset);

        FileRead fileRead = new FileRead(charset);
        String docPage = fileRead.readFromEntity(entity);

        FileWrite fileWrite = new FileWrite();
        fileWrite.writeInFile(docPage, rootDirectory, charset);

        try {
            EntityUtils.consume(entity);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
    }
}
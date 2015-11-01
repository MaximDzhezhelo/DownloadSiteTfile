package ua.kiev.makson.work_in_site;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;

public class FileRead {
    private static final Logger LOGGER = Logger.getLogger(FileRead.class
            .getName());

    public String readFromEntity(HttpEntity entity, String charset) {
        StringBuffer response = new StringBuffer();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                entity.getContent(), charset))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                inputLine = String.format("%s%n", inputLine);
                response.append(inputLine);
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        LOGGER.log(Level.SEVERE, "read Page of Site in " + charset);
        System.out.println(response);
        return response.toString();
    }

    public String readFromRootDirectory(File rootDirectory) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(
                rootDirectory))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                inputLine = String.format("%s%n", inputLine);
                sb.append(inputLine);
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        return sb.toString();
    }
}

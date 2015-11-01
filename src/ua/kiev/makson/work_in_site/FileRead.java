package ua.kiev.makson.work_in_site;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;

public class FileRead {

    public String readFromEntity(HttpEntity entity) {
        StringBuffer response = new StringBuffer();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                entity.getContent()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                inputLine = String.format("%s%n", inputLine);
                response.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

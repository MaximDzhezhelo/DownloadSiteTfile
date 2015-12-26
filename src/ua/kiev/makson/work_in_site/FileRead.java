package ua.kiev.makson.work_in_site;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.log4j.Logger;

public class FileRead {
	private static final Logger LOGGER = Logger.getLogger(FileRead.class);
	private String charset;

	public FileRead(String charset) {
		this.charset = charset;
	}

	public String readFromEntity(HttpEntity entity) {
		StringBuffer response = new StringBuffer();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent(), charset))) {
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				inputLine = String.format("%s%n", inputLine);
				response.append(inputLine);
			}
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		}
		LOGGER.info("read Page of Site in " + charset);
		return response.toString();
	}

	public String readFromRootDirectory(File rootDirectory) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(new FileInputStream(rootDirectory), charset))) {
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				inputLine = String.format("%s%n", inputLine);
				sb.append(inputLine);
			}
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		}
		return sb.toString();
	}
}

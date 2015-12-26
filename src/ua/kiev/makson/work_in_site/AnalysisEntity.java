package ua.kiev.makson.work_in_site;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.work_in_site.requests.Client;
import ua.kiev.makson.work_in_site.requests.GeneralHttpClient;
import ua.kiev.makson.work_in_site.requests.RequesAssistant;

public class AnalysisEntity {
	private static final Logger LOGGER = Logger.getLogger(AnalysisEntity.class);

	public void getDataEntity(CloseableHttpResponse response, RequesAssistant assistant) {
		LOGGER.info("run Analysis Entity");
		HttpEntity entity = response.getEntity();

		String charset = getCharset(response);

		GeneralHttpClient genClient = assistant.getGenClient();
		ControllerSite controlSite = assistant.getControlSite();
		File rootDirectory = controlSite.getRootDirectory();
		String defaultReadName = controlSite.getDefaultReadName();

		Client client = genClient.getClient();
		client.setCharset(charset);

		FileRead fileRead = new FileRead(charset);
		String docPage = fileRead.readFromEntity(entity);

		FileWrite fileWrite = new FileWrite();
		fileWrite.writeInFile(docPage, rootDirectory, charset, defaultReadName);

		try {
			EntityUtils.consume(entity);
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	private String getCharset(CloseableHttpResponse response) {
		ValueCharset valueCharset = new ValueCharset();
		return valueCharset.getTheValueOfCharset(response);
	}
}

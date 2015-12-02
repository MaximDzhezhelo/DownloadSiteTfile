package ua.kiev.makson.work_in_site.requests.getvideo;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import ua.kiev.makson.work_in_site.AnalysisEntity;
import ua.kiev.makson.work_in_site.requests.Client;
import ua.kiev.makson.work_in_site.requests.GeneralHttpClient;
import ua.kiev.makson.work_in_site.requests.RequesAssistant;

public class GetRequests implements Callable<Integer> {
	private String url;
	private RequesAssistant assistant;
	private int statusLine;

	public GetRequests(String url, RequesAssistant assistant) {
		this.url = url;
		this.assistant = assistant;
	}

	public int getStatusLine() {
		return statusLine;
	}

	public void setStatusLine(int statusLine) {
		this.statusLine = statusLine;
	}

	private static final Logger LOGGER = Logger.getLogger(GetRequests.class.getName());

	public void doGet() {
		LOGGER.log(Level.SEVERE, "getRequests");
		GeneralHttpClient genClient = assistant.getGenClient();
		Map<String, String> header = assistant.getHeader();
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
				builder.append(cookie.getName()).append('=').append(cookie.getValue()).append(';');
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

			if (debug) {
				statusLine = response.getStatusLine().getStatusCode();
				LOGGER.log(Level.SEVERE, "statusLine " + statusLine, statusLine);
			}

			AnalysisEntity entity = new AnalysisEntity();
			entity.getDataEntity(response, assistant);

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
	public Integer call() throws Exception {
		doGet();
		return statusLine;
	}
}

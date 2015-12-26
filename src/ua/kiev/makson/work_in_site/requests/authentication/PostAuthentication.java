package ua.kiev.makson.work_in_site.requests.authentication;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import ua.kiev.makson.work_in_site.requests.Client;
import ua.kiev.makson.work_in_site.requests.GeneralHttpClient;

public class PostAuthentication implements Callable<Integer> {
	private int statusLine;
	private String url;
	private Map<String, String> header;
	private Map<String, String> params;
	private GeneralHttpClient genClient;

	private static final Logger LOGGER = Logger.getLogger(PostAuthentication.class);

	public PostAuthentication(String url, Map<String, String> header, Map<String, String> params,
			GeneralHttpClient genClient) {
		this.url = url;
		this.header = header;
		this.params = params;
		this.genClient = genClient;
	}

	public int getStatusLine() {
		return statusLine;
	}

	public void setStatusLine(int statusLine) {
		this.statusLine = statusLine;
	}

	public void doPost() {
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
		HttpPost httpPost = new HttpPost(url);

		for (String key : header.keySet()) {
			httpPost.addHeader(key, header.get(key));
		}

		List<NameValuePair> nameValuePairs = new ArrayList<>();
		for (String name : params.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(name, params.get(name)));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} catch (UnsupportedEncodingException ex) {
			LOGGER.error(ex.getMessage());
		}

		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);

			if (debug) {
				statusLine = response.getStatusLine().getStatusCode();
				LOGGER.info("statusLine " + statusLine);
			}

			HttpEntity entity = response.getEntity();
			EntityUtils.consume(entity);
			cookies = cookieStore.getCookies();
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			if (response != null) {
				try {
					response.close();
					genClient.setClient(client);
				} catch (IOException ex) {
					LOGGER.error(ex.getMessage());
				}
			}
		}
	}

	@Override
	public Integer call() throws Exception {
		doPost();
		return statusLine;
	}
}

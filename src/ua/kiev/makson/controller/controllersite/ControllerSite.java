package ua.kiev.makson.controller.controllersite;

import java.io.File;

import javax.swing.JTextField;
import ua.kiev.makson.torrent.Executor;
import ua.kiev.makson.work_in_site.requests.GeneralHttpClient;

public class ControllerSite {
	private String urlString;
	private String login;
	private String password;
	private File rootDirectory;
	private boolean registration;
	private GeneralHttpClient genClient;
	private String defaultReadName;
	private JTextField registrationField;
	private ControllerWorkInSitePanel controllerWorkInSitePanel;

	public ControllerSite(String url, String login, String password, File rootDirectory) {
		this.urlString = url;
		this.login = login;
		this.password = password;
		this.rootDirectory = rootDirectory;
		genClient = new GeneralHttpClient();
		defaultReadName = "site.html";
	}

	public JTextField getRegistrationField() {
		return registrationField;
	}

	public void setRegistrationField(JTextField registrationField) {
		this.registrationField = registrationField;
	}

	public void setRegistration(boolean registration) {
		this.registration = registration;
	}

	public ControllerWorkInSitePanel getControllerWorkInSitePanel() {
		return controllerWorkInSitePanel;
	}

	public void setControllerWorkInSitePanel(ControllerWorkInSitePanel controllerWorkInSitePanel) {
		this.controllerWorkInSitePanel = controllerWorkInSitePanel;
	}

	public String getUrl() {
		return urlString;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public File getRootDirectory() {
		return rootDirectory;
	}

	public void setRootDirectory(File rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	public String getDefaultReadName() {
		return defaultReadName;
	}

	public void setTextFieldInWorkinSitePanel(Executor executor) {
		controllerWorkInSitePanel.setExecutor(executor);
	}

	public void setUrlLogPass(String url, String login, String password, File rootDirectory) {
		this.urlString = url;
		this.login = login;
		this.password = password;
		this.rootDirectory = rootDirectory;
	}

	public void loginStart() {
		genClient.authentication(this);
	}

	public void goInTheSite() {
		genClient.getVideo(this);
	}

	public void stopWorkInSite() {
		genClient.stopVideo();

	}

	public void stopAutentication() {
		genClient.stopAutentication();
	}

	public boolean getColorRegistration() {
		return registration;
	}

	public String getCharset() {
		return genClient.getClient().getCharset();
	}
}

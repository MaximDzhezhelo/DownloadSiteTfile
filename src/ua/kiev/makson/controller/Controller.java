package ua.kiev.makson.controller;

import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.controller.controllersite.ControllerWorkInSitePanel;
import ua.kiev.makson.torrent.Executor;
import ua.kiev.makson.work_in_site.FileRead;

public class Controller {
	private File rootDirectory;
	private File fileEditorPane;
	private SaveWayChoocerMenu selectDirectory;
	private SaveWayChoocerEditorPane selectFile;
	private FileRead read;
	private ControllerSite controlSite;
	private ControllerWorkInSitePanel controllerWorkInSitePanel;
	private JTextField registrationField;
	private Executor executor;

	public Controller() {
		controllerWorkInSitePanel = new ControllerWorkInSitePanel();
	}

	public File getRootDirectory() {
		return rootDirectory;
	}

	public void setRootDirectory(File rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	public ControllerSite getControlSite() {
		return controlSite;
	}

	public void setControlSite(ControllerSite controlSite) {
		this.controlSite = controlSite;
	}

	public void setRegistrationField(JTextField registrationField) {
		this.registrationField = registrationField;
	}

	public Executor getExecutor() {
		return executor;
	}

	public void setExecutor(Executor executor) {
		this.executor = executor;
	}

	public ControllerWorkInSitePanel getControllerWorkInSitePanel() {
		controllerWorkInSitePanel.setExecutor(executor);
		return controllerWorkInSitePanel;
	}

	public void setControllerWorkInSitePanel(ControllerWorkInSitePanel controllerWorkInSitePanel) {
		this.controllerWorkInSitePanel = controllerWorkInSitePanel;
	}

	public void openSelectDirectoryChoocer() {
		if (selectDirectory == null) {
			selectDirectory = new SaveWayChoocerMenu();
		}
		selectDirectory.createOpenJFileChoocer(this);
	}

	public void OpenSelectFileChoocer() {
		if (selectFile == null) {
			selectFile = new SaveWayChoocerEditorPane();
		}
		fileEditorPane = selectFile.createOpenJFileChoocer(rootDirectory);
	}

	public String readSelectFile() {
		if (read == null) {
			String charset = controlSite.getCharset();
			read = new FileRead(charset);
		} else {
			if (fileEditorPane == null) {
				return "if you choose the file for viewing?";
			}
		}
		String textResult = read.readFromRootDirectory(fileEditorPane);
		return textResult;
	}

	public void sendLogPassUrl(String urlString, String login, char[] pas) {
		ConvertPassword convert = new ConvertPassword();
		String password = convert.getPassword(pas);
		if (login != null && password != null) {
			if (controlSite == null) {
				controlSite = new ControllerSite(urlString, login, password, rootDirectory);
			} else {
				controlSite.setUrlLogPass(urlString, login, password, rootDirectory);
			}
			controlSite.setRegistrationField(registrationField);
			controlSite.loginStart();
			setRegistrationColor();
		}
	}

	/*
	 * call ControllerSite and return color value
	 */
	public boolean setRegistrationColor() {
		return controlSite.getColorRegistration();
	}

	public void goToTheSite() {
		if (controlSite == null) {
			JOptionPane.showMessageDialog(null, "пройдите аутентификацию");
		}
		controlSite.setRootDirectory(rootDirectory);
		controlSite.setControllerWorkInSitePanel(controllerWorkInSitePanel);
		controlSite.goInTheSite();
	}

	public void stopToTheSite() {
		if (controlSite == null) {
			JOptionPane.showMessageDialog(null, "пройдите аутентификацию");
		}
		controlSite.stopWorkInSite();
	}

	public void stopAutentication() {
		controlSite.stopAutentication();
	}

}

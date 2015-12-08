package ua.kiev.makson.controller;

import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.work_in_site.FileRead;

public class Controller {
	private File rootDirectory;
	private File fileEditorPane;
	private SaveWayChoocerMenu selectDirectory;
	private SaveWayChoocerEditorPane selectFile;
	private FileRead read;
	private ControllerSite controlSite;
	private JTextField registrationField;

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
			controlSite.shoow();
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
		controlSite.goInTheSite();
	}

	public void stopToTheSite() {
		if (controlSite == null) {
			JOptionPane.showMessageDialog(null, "пройдите аутентификацию");
		}
	}
}

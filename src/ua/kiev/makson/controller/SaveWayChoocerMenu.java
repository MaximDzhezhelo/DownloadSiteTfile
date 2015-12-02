package ua.kiev.makson.controller;

import java.io.File;
import java.util.Properties;
import javax.swing.JFileChooser;

public class SaveWayChoocerMenu extends JFileChooser {

	private static final long serialVersionUID = 1L;
	private Properties properties;
	private File rootDirectory;

	public SaveWayChoocerMenu() {
		properties = System.getProperties();
		rootDirectory = new File(properties.getProperty("user.home"));
	}

	public void createOpenJFileChoocer(Controller control) {
		setCurrentDirectory(rootDirectory);
		setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		setAcceptAllFileFilterUsed(false);
		int ret = showDialog(null, "Выберите папку");
		if (ret == JFileChooser.APPROVE_OPTION) {
			rootDirectory = getSelectedFile();
		}
		control.setRootDirectory(rootDirectory);
	}

}

package ua.kiev.makson.torrent;

import javax.swing.JPanel;

import ua.kiev.makson.controller.Controller;
import ua.kiev.makson.gui.MyFrame;

public class PanelDownloadTorrent extends JPanel {

	private static final long serialVersionUID = 1L;
	private Executor executor;

	public PanelDownloadTorrent(MyFrame frame) {
		executor = new Executor(this);
		addExecutorInControllerWorkInSitePanel(frame);
	}

	public void addExecutorInControllerWorkInSitePanel(MyFrame frame) {
		Controller controller = frame.getControl();
		controller.setExecutor(executor);
	}

}

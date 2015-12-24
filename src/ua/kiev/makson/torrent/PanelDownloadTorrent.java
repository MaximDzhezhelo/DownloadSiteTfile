package ua.kiev.makson.torrent;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelDownloadTorrent extends JPanel {

	private static final long serialVersionUID = 1L;
	private Executor executor;
	private static final Logger LOGGER = Logger.getLogger(PanelDownloadTorrent.class.getName());

	public PanelDownloadTorrent(JFrame frame) {
		frame.getContentPane().add(this);
	}

	public Executor getExecutor() {
		return executor;
	}

	public void setExecutor(Executor executor) {
		this.executor = executor;
	}

	public void createExecutor() {
		executor = new Executor(this);
		LOGGER.log(Level.SEVERE, "createExecutor");
	}

}

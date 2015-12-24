package ua.kiev.makson.torrent;

import java.awt.Color;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import ua.kiev.makson.work_in_site.requests.getvideo.page.VideoDescription;

public class Torrent extends JPanel implements Callable<JPanel> {

	private static final long serialVersionUID = 1L;
	private JProgressBar jProgressBar;
	private Box box;
	private VideoDescription description;
	private static final Logger LOGGER = Logger.getLogger(Torrent.class.getName());

	public Torrent(Box box, VideoDescription description) {
		this.box = box;
		this.description = description;
	}

	public void createPanel() {
		createProgress();
		JLabel jLabel = new JLabel(description.getName(), 15);
		add(jLabel);
		add(jProgressBar);
	}

	public void createProgress() {
		jProgressBar = new JProgressBar();
		jProgressBar.setStringPainted(true);
		jProgressBar.setForeground(Color.GREEN);
	}

	public void startDownloadTorent() {
		createPanel();
		box.add(this);
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(1000);
				jProgressBar.setValue(i);
			} catch (InterruptedException ex) {
				LOGGER.log(Level.SEVERE, ex.getMessage());
			}
		}
	}

	@Override
	public JPanel call() throws Exception {
		startDownloadTorent();
		return this;
	}

}

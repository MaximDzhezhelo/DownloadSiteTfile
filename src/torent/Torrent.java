package torent;

import java.awt.Color;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Torrent extends JPanel implements Callable<JPanel> {

	private static final long serialVersionUID = 1L;
	private JProgressBar jProgressBar;
	private Box box;
	private static final Logger LOGGER = Logger.getLogger(Torrent.class.getName());

	public Torrent(Box box) {
		this.box = box;
	}

	public void createPanel() {
		createProgress();
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

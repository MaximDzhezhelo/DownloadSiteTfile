package torent;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelDownloadTorrent extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Executor sheduler;
	private static final Logger LOGGER = Logger.getLogger(PanelDownloadTorrent.class.getName());

	public PanelDownloadTorrent(JFrame frame) {
		this.frame = frame;
	}

	public void startDownloadTorrent() {
		frame.getContentPane().add(this);
		sheduler = new Executor(this);
		try {
			sheduler.startExecutor();
		} catch (InterruptedException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage());
		} catch (ExecutionException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage());
		}

	}

}

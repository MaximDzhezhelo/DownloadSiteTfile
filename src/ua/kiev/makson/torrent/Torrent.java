package ua.kiev.makson.torrent;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

import ua.kiev.makson.work_in_site.requests.getvideo.page.VideoDescription;

public class Torrent extends JPanel implements Callable<JPanel> {

	private static final long serialVersionUID = 1L;
	private JProgressBar jProgressBar;
	private Box box;
	private VideoDescription description;
	private Client client;
	private static final Logger LOGGER = Logger.getLogger(Torrent.class.getName());

	public Torrent(Box box, VideoDescription description) {
		this.box = box;
		this.description = description;
	}

	public void createPanel() {
		createProgress();
		JLabel jLabel = new JLabel(description.getName());
		add(jLabel);
		add(jProgressBar);
		box.add(this);
		box.revalidate();
		box.repaint();
	}

	public void createProgress() {
		jProgressBar = new JProgressBar();
		jProgressBar.setStringPainted(true);
		jProgressBar.setForeground(Color.GREEN);
	}

	public void startDownloadTorent() {
		createPanel();
		String torrent = description.getDownloadTorrent();
		File fileTorrent = new File(torrent);
		String wayFile = makesWay(torrent);
		File file = new File(wayFile);
		try {
			client = new Client(InetAddress.getLocalHost(), SharedTorrent.fromFile(fileTorrent, file));

			client.setMaxDownloadRate(50.0);
			client.setMaxUploadRate(50.0);
			client.addObserver(new Observer() {
				@Override
				public void update(Observable o, Object arg) {
					Client c = (Client) o;
					if (c.getTorrent().isInitialized()) {
						final float completion = c.getTorrent().getCompletion();
						System.out.printf(">>>> torrent is %.2f%% complete.%n", completion);
						SwingUtilities.invokeLater(() -> jProgressBar.setValue(Math.round(completion)));
					}
				}
			});

		} catch (UnknownHostException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage());
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage());
		}
	}

	private String makesWay(String str) {
		int index = str.lastIndexOf('/');
		return str.substring(0, index + 1);
	}

	@Override
	public JPanel call() throws Exception {
		startDownloadTorent();
		return this;
	}

}

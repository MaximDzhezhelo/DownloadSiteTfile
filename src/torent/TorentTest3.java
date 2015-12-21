package torent;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

public class TorentTest3 extends JPanel {
	private JProgressBar jProgressBar;

	public void createPanel() {
		createProgress();
		add(jProgressBar);
//		startDownloadTorent();
	}

	public void createProgress() {
		jProgressBar = new JProgressBar();
		jProgressBar.setStringPainted(true);
		jProgressBar.setForeground(Color.GREEN);
	}

	public void startDownloadTorent() {
		File filet = new File("/home/makson/Документы/Экспериментатор/Экспериментатор.torrent");
		File fileD = new File("/home/makson/Документы/Экспериментатор/");
		Logger LOGGER = Logger.getLogger(TorentTest.class.getName());
		try {
			Client client = new Client(InetAddress.getLocalHost(), SharedTorrent.fromFile(filet, fileD));

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

		} catch (UnknownHostException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}
}

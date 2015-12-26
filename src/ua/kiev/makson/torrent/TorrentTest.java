package ua.kiev.makson.torrent;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

public class TorrentTest {

	public static void main(String[] args) {
		File fileTorrent = new File("/home/makson/Документы/Глюки/Глюки.torrent");
		// String wayFile = makesWay();
		File file = new File("/home/makson/Документы/Глюки/");
		try {
			Client client = new Client(InetAddress.getLocalHost(), SharedTorrent.fromFile(fileTorrent, file));

			client.setMaxDownloadRate(1000.0);
			client.setMaxUploadRate(1000.0);
			client.addObserver(new Observer() {
				@Override
				public void update(Observable o, Object arg) {
					Client c = (Client) o;
					if (c.getTorrent().isInitialized()) {
						final float completion = c.getTorrent().getCompletion();
						System.out.printf(">>>> torrent is %.2f%% complete.%n", completion);
						// SwingUtilities.invokeLater(() ->
						// jProgressBar.setValue(Math.round(completion)));
					}
				}
			});
			client.download();
		} catch (UnknownHostException ex) {
			// LOGGER.log(Level.SEVERE, ex.getMessage());
		} catch (IOException ex) {
			// LOGGER.log(Level.SEVERE, ex.getMessage());
		}

	}

}

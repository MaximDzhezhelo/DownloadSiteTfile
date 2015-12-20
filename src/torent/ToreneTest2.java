package torent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import org.apache.log4j.BasicConfigurator;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

public class ToreneTest2 extends JFrame {

	public static void main(String[] args) {
		ToreneTest2 table = new ToreneTest2();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension dim = kit.getScreenSize();
		int width = dim.width / 4;
		int height = dim.height / 6;
		table.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table.setSize(width, height);
		table.setLocationRelativeTo(null);

		JProgressBar jProgressBar = new JProgressBar();
		jProgressBar.setStringPainted(true);
		jProgressBar.setForeground(Color.GREEN);

		table.add(jProgressBar);
		table.setVisible(true);

		// for (int i = 1; i < 11; i++) {
		// try {
		// Thread.sleep(1000);
		// jProgressBar.setValue((int) Math.round(i));
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		// BasicConfigurator.configure();
		File filet = new File(
				"/home/makson/Документы/Ловец акул с острова Бора-Бора/Ловец акул с острова Бора-Бора.torrent");
		File fileD = new File("/home/makson/Документы/Ловец акул с острова Бора-Бора/");
		Logger LOGGER = Logger.getLogger(TorentTest.class.getName());
		try {
			Client client = new Client(InetAddress.getLocalHost(), SharedTorrent.fromFile(filet, fileD));
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
			client.setMaxDownloadRate(50.0);
			client.setMaxUploadRate(50.0);
			client.download();

		} catch (UnknownHostException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}

	}

}

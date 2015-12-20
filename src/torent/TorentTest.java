package torent;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.log4j.BasicConfigurator;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

public class TorentTest {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		File filet = new File("/home/makson/Документы/Аллигатор/Аллигатор.torrent");
		File fileD = new File("/home/makson/Документы/Аллигатор/");
		Logger LOGGER = Logger.getLogger(TorentTest.class.getName());
		try {
			Client client = new Client(InetAddress.getLocalHost(), SharedTorrent.fromFile(filet, fileD));
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

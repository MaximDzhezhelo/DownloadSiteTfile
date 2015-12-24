package ua.kiev.makson.torrent;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.JPanel;

import ua.kiev.makson.work_in_site.requests.getvideo.page.VideoDescription;

public class Executor {
	private ExecutorService executor;
	private ArrayList<Torrent> arrayList;
	private ArrayList<Future<JPanel>> result;
	private Box box;
	private JPanel panelMain;
	private static final Logger LOGGER = Logger.getLogger(Executor.class.getName());

	public Executor(JPanel panel) {
		executor = Executors.newFixedThreadPool(4);
		this.panelMain = panel;
		box = Box.createVerticalBox();
		arrayList = new ArrayList<>();
		panel.add(box);
	}

	public void startExecutor() {
		result = new ArrayList<Future<JPanel>>();
		for (Torrent torent : arrayList) {
			result.add(executor.submit(torent));
		}
		for (Future<JPanel> future : result) {

			try {
				JPanel panel = future.get();
				box.remove(panel);
				panelMain.revalidate();
				panelMain.repaint();
			} catch (InterruptedException ex) {
				LOGGER.log(Level.SEVERE, ex.getMessage());
			} catch (ExecutionException ex) {
				LOGGER.log(Level.SEVERE, ex.getMessage());
			}

		}
	}

	public void executorStartDownload(VideoDescription... description) {
		for (VideoDescription videoDescription : description) {
			arrayList.add(new Torrent(box, videoDescription));
		}
		startExecutor();
		arrayList.clear();
	}

	public void executorStopDownload() {
		if (!executor.isShutdown()) {
			executor.shutdownNow();
		}
	}

}

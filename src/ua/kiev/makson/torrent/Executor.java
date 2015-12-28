package ua.kiev.makson.torrent;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.Box;
import javax.swing.JPanel;
import org.apache.log4j.Logger;

import ua.kiev.makson.work_in_site.requests.getvideo.page.VideoDescription;

public class Executor implements Runnable {
	private ExecutorService executor;
	private ArrayList<Torrent> arrayList;
	private ConcurrentLinkedQueue<Future<JPanel>> result;
	private Box box;
	private JPanel panelMain;
	private static final Logger LOGGER = Logger.getLogger(Executor.class);

	public Executor(JPanel panel) {
		executor = Executors.newFixedThreadPool(4);
		this.panelMain = panel;
		result = new ConcurrentLinkedQueue<>();
		box = Box.createVerticalBox();
		arrayList = new ArrayList<>();
		panel.add(box);
	}

	public void startExecutor() {

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
				LOGGER.error(ex.getMessage());
			} catch (ExecutionException ex) {
				LOGGER.error(ex.getMessage());
			}

		}
	}

	public void executorStartDownload(VideoDescription description) {
		result.clear();
		arrayList.clear();
		arrayList.add(new Torrent(box, description));
		new Thread(this).start();

	}

	public void executorStopDownload() {
		if (!executor.isShutdown()) {
			executor.shutdownNow();
		}
	}

	@Override
	public void run() {
		startExecutor();
	}

}

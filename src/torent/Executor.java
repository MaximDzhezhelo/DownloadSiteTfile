package torent;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.JPanel;

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
		panel.add(box);
	}

	public void startExecutor() throws InterruptedException, ExecutionException {
		startList();
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
		executor.shutdown();
	}

	public void startList() {
		arrayList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			arrayList.add(new Torrent(box));
		}

	}

}

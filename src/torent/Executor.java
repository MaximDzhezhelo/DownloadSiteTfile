package torent;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.Box;
import javax.swing.JPanel;

public class Executor {
	private ExecutorService executor;
	private ArrayList<Torent> arrayList;
	private ArrayList<Future<JPanel>> result;
	private Box box;
	private JPanel panelM;

	public Executor(JPanel panel) {
		executor = Executors.newFixedThreadPool(4);
		this.panelM = panel;
		box = Box.createVerticalBox();
		panel.add(box);
	}

	public void startExecutor() throws InterruptedException, ExecutionException {
		startList();
		result = new ArrayList<Future<JPanel>>();
		for (Torent torent : arrayList) {
			result.add(executor.submit(torent));
		}
		for (Future<JPanel> future : result) {
			try {
				JPanel panel = future.get();
				box.remove(panel);
				panelM.revalidate();
				panelM.repaint();

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
	}

	public void startList() {
		arrayList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			arrayList.add(new Torent(box));
		}

	}

}

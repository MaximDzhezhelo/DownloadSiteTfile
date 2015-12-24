package torent;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JPanel;

public class Executor {
	private ExecutorService executor;
	private ArrayList<Torent> arrayList;
	private ArrayList<Future<Integer>> result;
	private JPanel panel;

	public Executor(JPanel panel) {
		executor = Executors.newFixedThreadPool(4);
		this.panel = panel;
	}

	public void startExecutor() throws InterruptedException, ExecutionException {
		startList();
		result = new ArrayList<Future<Integer>>();
		for (Torent torent : arrayList) {
			result.add(executor.submit(torent));
		}
		for (Future<Integer> future : result) {
			try {
				if (future.get() != null) {
					System.out.println("all");
				}
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
			arrayList.add(new Torent(panel));
		}

	}

}

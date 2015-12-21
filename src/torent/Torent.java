package torent;

import java.awt.Color;
import java.util.concurrent.Callable;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Torent extends JPanel implements Callable<Integer> {
	private JProgressBar jProgressBar;

	public void createPanel() {
		createProgress();
		add(jProgressBar);
		// startDownloadTorent();
	}

	public void createProgress() {
		jProgressBar = new JProgressBar();
		jProgressBar.setStringPainted(true);
		jProgressBar.setForeground(Color.GREEN);
	}

	public void startDownloadTorent() {
		for (int i = 0; i < 15; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i);

		}
	}

	@Override
	public Integer call() throws Exception {
		startDownloadTorent();
		return 12;
	}

}

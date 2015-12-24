package torent;

import java.awt.Color;
import java.util.concurrent.Callable;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Torent extends JPanel implements Callable<Integer> {

	private static final long serialVersionUID = 1L;
	private JProgressBar jProgressBar;
	private JPanel panel;

	public Torent(JPanel panel) {
		this.panel = panel;
	}

	public void createPanel() {
		createProgress();
		add(jProgressBar);
	}

	public void createProgress() {
		jProgressBar = new JProgressBar();
		jProgressBar.setStringPainted(true);
		jProgressBar.setForeground(Color.GREEN);
	}

	public void startDownloadTorent() {
		createPanel();
		panel.add(this);
		for (int i = 0; i < 15; i++) {
			try {
				Thread.sleep(1000);
				jProgressBar.setValue(i);
			} catch (InterruptedException e) {
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

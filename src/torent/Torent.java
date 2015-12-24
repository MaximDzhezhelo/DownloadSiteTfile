package torent;

import java.awt.Color;
import java.util.concurrent.Callable;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Torent extends JPanel implements Callable<JPanel> {

	private static final long serialVersionUID = 1L;
	private JProgressBar jProgressBar;
	private Box box;

	public Torent(Box box) {
		this.box = box;
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
		box.add(this);
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(1000);
				jProgressBar.setValue(i);
			} catch (InterruptedException e) {
			}
			System.out.println(i);
		}
	}

	@Override
	public JPanel call() throws Exception {
		startDownloadTorent();
		return this;
	}

}

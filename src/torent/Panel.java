package torent;

import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel extends JPanel {
	private JFrame frame;
	private Executor sheduler;

	public Panel(JFrame frame) {
		this.frame = frame;
	}

	public void startDownloadTorrent() {
		frame.getContentPane().add(this);
		sheduler = new Executor(this);
		try {
			sheduler.startExecutor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}

}

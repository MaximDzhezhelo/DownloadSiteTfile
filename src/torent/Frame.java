package torent;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.concurrent.ExecutionException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {
	private ToreneTest2 toreneTest2;
	private TorentTest1 toreneTest1;
	private TorentTest3 toreneTest3;

	public void createFrame() {
		Frame frame = new Frame();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension dim = kit.getScreenSize();
		int width = dim.width / 4;
		int height = dim.height / 6;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		Panel panel = new Panel(frame);
		panel.startDownloadTorrent();
		
		
		// Box box = Box.createVerticalBox();
		// box.add(toreneTest1);
		// box.add(toreneTest2);
		// box.add(toreneTest3);
		// frame.add(box);
	}

	public void createTorent() {
		toreneTest2 = new ToreneTest2();
		toreneTest2.createPanel();
		toreneTest1 = new TorentTest1();
		toreneTest1.createPanel();
		toreneTest3 = new TorentTest3();
		toreneTest3.createPanel();
	}

	public static void main(String[] arg) {
		Frame frame = new Frame();
		frame.createFrame();
	}
}

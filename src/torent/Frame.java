package torent;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

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
		PanelDownloadTorrent panel = new PanelDownloadTorrent(frame);
		panel.startDownloadTorrent();
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

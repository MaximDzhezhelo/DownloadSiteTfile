package ua.kiev.makson.torrent;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;

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
//		PanelDownloadTorrent panel = new PanelDownloadTorrent(frame);
	}

	public static void main(String[] arg) {
		Frame frame = new Frame();
		frame.createFrame();
	}
}

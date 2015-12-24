package ua.kiev.makson.gui.panel.workinsitepanel;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import ua.kiev.makson.gui.MyFrame;
import ua.kiev.makson.gui.panel.table.TablePanel;
import ua.kiev.makson.torrent.PanelDownloadTorrent;

public class WorkInSitePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private TablePanel tablePanel;
	private PanelDownloadTorrent downloadTorrent;
	private MyFrame frame;

	private ButtonPanel buttonPanel;

	public WorkInSitePanel(MyFrame frame) {
		this.frame = frame;
	}

	public void createPanelWorkInSitePanel() {
		createPanel();
	}

	public void createPanel() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		createTablePanel();
		createButtonPanel();
		createPanelDownloadTorrent();
		panel.add(buttonPanel, BorderLayout.BEFORE_FIRST_LINE);
		panel.add(tablePanel, BorderLayout.CENTER);
		panel.add(downloadTorrent, BorderLayout.AFTER_LAST_LINE);
		this.add(panel);
	}

	public void createTablePanel() {
		tablePanel = new TablePanel();
		tablePanel.createTablePanel();
	}

	public void createButtonPanel() {
		buttonPanel = new ButtonPanel(frame, tablePanel);
		buttonPanel.createPanel();
	}

	public void createPanelDownloadTorrent() {
		downloadTorrent = new PanelDownloadTorrent(frame);
	}

}

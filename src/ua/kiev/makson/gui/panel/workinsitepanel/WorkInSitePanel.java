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
	private TextFieldPanel fieldPanel;
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
		createTextFieldPanel();
		createButtonPanel();
		createPanelDownloadTorrent();
		panel.add(buttonPanel, BorderLayout.BEFORE_FIRST_LINE);
		panel.add(fieldPanel, BorderLayout.CENTER);
		panel.add(tablePanel, BorderLayout.AFTER_LAST_LINE);
		panel.add(downloadTorrent, BorderLayout.WEST);
		this.add(panel);
	}

	public void createTablePanel() {
		tablePanel = new TablePanel();
		tablePanel.createTablePanel();
	}

	public void createTextFieldPanel() {
		fieldPanel = new TextFieldPanel(frame, tablePanel);
		fieldPanel.createPanel();
	}

	public void createButtonPanel() {
		buttonPanel = new ButtonPanel(frame, fieldPanel);
		buttonPanel.createPanel();
	}

	public void createPanelDownloadTorrent() {
		downloadTorrent = new PanelDownloadTorrent(frame);
	}

}

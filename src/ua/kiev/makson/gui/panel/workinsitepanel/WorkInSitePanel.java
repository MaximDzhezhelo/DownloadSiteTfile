package ua.kiev.makson.gui.panel.workinsitepanel;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import ua.kiev.makson.gui.MyFrame;
import ua.kiev.makson.gui.panel.table.TablePanel;

public class WorkInSitePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private TablePanel tablePanel;
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
		// panel.add(panelBut, BorderLayout.BEFORE_FIRST_LINE);
		panel.add(buttonPanel, BorderLayout.BEFORE_FIRST_LINE);
		// panel.add(panelText, BorderLayout.CENTER);
		panel.add(tablePanel, BorderLayout.AFTER_LAST_LINE);
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

}

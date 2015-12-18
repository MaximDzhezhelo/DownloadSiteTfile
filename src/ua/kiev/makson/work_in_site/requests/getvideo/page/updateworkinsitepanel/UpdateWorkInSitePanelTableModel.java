package ua.kiev.makson.work_in_site.requests.getvideo.page.updateworkinsitepanel;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.controller.controllersite.ControllerWorkInSitePanel;
import ua.kiev.makson.gui.panel.table.TableModel;

public class UpdateWorkInSitePanelTableModel extends UpdateWorkInSitePanel {
	private ControllerWorkInSitePanel controllerWorkInSitePanel;
	private TableModel model;
	private String str;

	public UpdateWorkInSitePanelTableModel(ControllerSite controlSite, String str) {
		this.controllerWorkInSitePanel = controlSite.getControllerWorkInSitePanel();
		model = controllerWorkInSitePanel.getTableModel();
		this.str = str;
	}

	public void updateCount() {
		model.updateData(str);
	}

	@Override
	public void run() {
		super.run();
	}
}

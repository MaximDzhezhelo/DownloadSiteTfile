package ua.kiev.makson.work_in_site.requests.getvideo.page.updateworkinsitepanel;

import javax.swing.JTextField;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.controller.controllersite.ControllerWorkInSitePanel;

public class UpdateWorkInSitePanelCountField extends UpdateWorkInSitePanel {
	private ControllerWorkInSitePanel controllerWorkInSitePanel;
	private JTextField countField;

	public UpdateWorkInSitePanelCountField(ControllerSite controlSite) {
		controllerWorkInSitePanel = controlSite.getControllerWorkInSitePanel();
		countField = controllerWorkInSitePanel.getCount();
	}

	public void updateCount() {
		String count = countField.getText();
		if (count.equals("") || count == null) {
			count = "0";
		}
		int x = (Integer) Integer.valueOf(count);
		x++;
		countField.setText(Integer.toString(x));
	}

	@Override
	public void run() {
		super.run();
	}
}

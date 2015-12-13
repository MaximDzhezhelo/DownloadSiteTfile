package ua.kiev.makson.work_in_site.requests.getvideo.page;

import java.awt.EventQueue;

import javax.swing.JTextField;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.controller.controllersite.ControllerWorkInSitePanel;

public class UpdateWorkInSitePanel implements Runnable {
	private ControllerSite controlSite;
	private ControllerWorkInSitePanel controllerWorkInSitePanel;
	private JTextField countField;
	private JTextField leftTimeField;
	private JTextField loadingField;

	public UpdateWorkInSitePanel(ControllerSite controlSite) {
		this.controlSite = controlSite;
		controllerWorkInSitePanel = controlSite.getControllerWorkInSitePanel();
		countField = controllerWorkInSitePanel.getCount();
		leftTimeField = controllerWorkInSitePanel.getLeftTime();
		loadingField = controllerWorkInSitePanel.getLoading();
	}

	private void updateCount() {
		System.out.println("updateCount()");
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
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				System.out.println("run updateCount()");
				updateCount();
			}
		});
	}
}

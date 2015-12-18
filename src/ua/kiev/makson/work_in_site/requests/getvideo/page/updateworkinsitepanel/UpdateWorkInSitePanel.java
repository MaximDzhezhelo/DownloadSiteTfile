package ua.kiev.makson.work_in_site.requests.getvideo.page.updateworkinsitepanel;

import java.awt.EventQueue;

public class UpdateWorkInSitePanel implements Runnable {

	public void updateCount() {
	}

	@Override
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				updateCount();
			}
		});
	}
}

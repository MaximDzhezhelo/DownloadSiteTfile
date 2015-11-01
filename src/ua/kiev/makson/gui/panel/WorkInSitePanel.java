package ua.kiev.makson.gui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import ua.kiev.makson.controller.Controller;
import ua.kiev.makson.gui.MyFrame;

public class WorkInSitePanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JButton butStart;
    private JButton butStop;
    private MyFrame frame;
    private Controller control;

    public WorkInSitePanel(MyFrame frame) {
        this.frame = frame;
    }

    public void createPanelWorkInSitePanel() {
        createPanel();
    }

    public void createPanel() {
        panel = new JPanel();
        createButton();
        this.add(panel);

    }

    public void createButton() {
        butStart = new JButton("Start");
        butStart.addActionListener(this);
        butStop = new JButton("Stop");
        butStop.addActionListener(this);
        panel.add(butStart);
        panel.add(butStop);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e == null) {
            return;
        }
        if (e.getSource() instanceof JButton) {
            JButton buton = (JButton) e.getSource();
            if (buton.getText().equals("Start")) {
                control = frame.getControl();
                control.goToTheSite();
                frame.setControl(control);
            } else if (buton.getText().equals("Stop")) {
                control = frame.getControl();
                control.stopToTheSite();
                frame.setControl(control);
            }
        }
    }
}

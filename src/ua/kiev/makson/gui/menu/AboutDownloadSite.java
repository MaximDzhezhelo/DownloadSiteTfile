package ua.kiev.makson.gui.menu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutDownloadSite extends JDialog {

    private static final long serialVersionUID = 1L;
    private Box box;
    private JPanel panelBut;

    public AboutDownloadSite(JFrame frame) {
        super(frame, "AboutDownloadSite", true);
        createPanel();
        createPanelBut();
        add(box, BorderLayout.CENTER);
        add(panelBut, BorderLayout.AFTER_LAST_LINE);
        setSize(350, 150);
        setLocationRelativeTo(null);
    }

    public void createPanel() {
        JPanel panel2 = new JPanel();
        JLabel name = new JLabel(
                "<html><h1><i> DownloadSite <br> </i></h1><hr></html>");
        panel2.add(name);
        JPanel panel3 = new JPanel();
        JLabel text = new JLabel(
                "<html> By Maxim Dzhezhelo <br>version - 1. 2015</html>");
        panel3.add(text);
        box = Box.createVerticalBox();
        box.add(panel2);
        box.add(panel3);
    }

    public void createPanelBut() {
        panelBut = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        panelBut.add(ok);
    }
}
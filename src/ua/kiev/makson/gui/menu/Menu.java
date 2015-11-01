package ua.kiev.makson.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ua.kiev.makson.controller.Controller;
import ua.kiev.makson.gui.MyFrame;
import ua.kiev.makson.gui.menu.itemlogin.MenuLogPassJDialog;

public class Menu extends JMenuBar implements ActionListener {

    private static final long serialVersionUID = 1L;
    private MyFrame frame;
    private AboutDownloadSite about;
    private MenuLogPassJDialog loginDialog;
    private Controller control;

    public Menu(MyFrame frame) {
        this.frame = frame;
        this.control = frame.getControl();
    }

    public Controller getControl() {
        return control;
    }

    public void createMenu() {
        JMenu mnNewMenu_1 = new JMenu("File");
        add(mnNewMenu_1);

        JMenuItem open = new JMenuItem("save");
        open.addActionListener(this);
        mnNewMenu_1.add(open);

        JMenuItem exit = new JMenuItem("exit");
        exit.addActionListener(this);
        mnNewMenu_1.add(exit);

        JMenu mnNewMenu = new JMenu("Autentication");
        add(mnNewMenu);

        JMenuItem login = new JMenuItem("login");
        login.addActionListener(this);
        mnNewMenu.add(login);

        JMenu aboutMenu = new JMenu("About");
        add(aboutMenu);

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(this);
        aboutMenu.add(aboutItem);
        frame.setJMenuBar(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e == null) {
            return;
        }
        JMenuItem source = (JMenuItem) e.getSource();
        String nameMenu = source.getText();
        if (nameMenu.equals("exit")) {
            frame.dispose();
        } else if (nameMenu.equals("About")) {
            if (about == null) {
                about = new AboutDownloadSite(frame);
            }
            about.setVisible(true);
        } else if (nameMenu.equals("save")) {
            control.openSelectDirectoryChoocer();
            frame.setControl(control);
        } else if (nameMenu.equals("login")) {
            if (loginDialog == null) {
                loginDialog = new MenuLogPassJDialog(frame);
            }
            loginDialog.setVisible(true);
        }
    }
}

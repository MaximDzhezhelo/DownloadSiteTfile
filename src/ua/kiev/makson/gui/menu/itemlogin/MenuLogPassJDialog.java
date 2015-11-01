package ua.kiev.makson.gui.menu.itemlogin;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import ua.kiev.makson.gui.MyFrame;

public class MenuLogPassJDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private int width = 0;
    private int height = 0;
    private MyFrame frame;

    public MenuLogPassJDialog(MyFrame frame) {
        super(frame, "Login", true);
        this.frame = frame;
        createDimension();
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        createLoginPanel();
    }

    public void createDimension() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        width = dim.width / 4;
        height = dim.height / 6;
    }

    public void createLoginPanel() {
        MenuPanelLogPass panel = new MenuPanelLogPass(this, frame);
        panel.createPanel();
        add(panel);
    }

}

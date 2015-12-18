package ua.kiev.makson.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import ua.kiev.makson.controller.Controller;
import ua.kiev.makson.gui.menu.Menu;
import ua.kiev.makson.gui.panel.MyPanelJEditorPane;
import ua.kiev.makson.gui.panel.WorkInSitePanel;
import ua.kiev.makson.gui.panel.table.TablePanel;

public class MyFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private int width = 0;
    private int height = 0;
    private JTabbedPane jTabbedPane;
    private MyPanelJEditorPane panelJEditorPane;
    private WorkInSitePanel workInSitePanel;
    private TablePanel table;
    private Menu menu;
    private Controller control;

    public MyFrame() {
        control = new Controller();
        createFrame();
    }

    public Controller getControl() {
        return control;
    }

    public void setControl(Controller control) {
        this.control = control;
    }

    public void createDimension() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        width = dim.width / 2;
        height = dim.height / 2;
    }

    public void createFrame() {
        createDimension();
        setTitle("Download Site");
        setSize(width, height);
        createImageIcon();
        createMyMenu();
        createJTabbedPane();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    public void createImageIcon() {
        BufferedImage buff_image;
        try {
            buff_image = ImageIO.read(getClass().getResourceAsStream(
                    "res/bird.jpeg"));
            setIconImage(buff_image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createJTabbedPane() {
        jTabbedPane = new JTabbedPane();
        createMyPanelJEditorPane();
        createWorkInSitePanel();
        createTablePanel();
        jTabbedPane.add("HTML", panelJEditorPane);
        jTabbedPane.add("Site", workInSitePanel);
        jTabbedPane.add("Table", table);
        this.getContentPane().add(jTabbedPane);

    }

    public void createMyPanelJEditorPane() {
        panelJEditorPane = new MyPanelJEditorPane(this);
        panelJEditorPane.createMyPanelJEditorPane();
    }

    public void createWorkInSitePanel() {
        workInSitePanel = new WorkInSitePanel(this);
        workInSitePanel.createPanelWorkInSitePanel();
    }

    public void createTablePanel() {
        table = new TablePanel(this);
        table.createTablePanel();
    }

    public void createMyMenu() {
        menu = new Menu(this);
        menu.createMenu();
    }
}
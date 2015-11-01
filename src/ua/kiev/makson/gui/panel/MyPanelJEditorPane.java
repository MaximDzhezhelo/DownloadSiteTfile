package ua.kiev.makson.gui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ua.kiev.makson.controller.Controller;
import ua.kiev.makson.gui.MyFrame;

public class MyPanelJEditorPane extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JEditorPane editorPane;
    private JButton openBut;
    private JCheckBox editable;
    private JButton loadButton;
    private JScrollPane editorscroll;
    private MyFrame frame;
    private String textResult;
    private Controller control;

    public MyPanelJEditorPane(MyFrame frame) {
        this.frame = frame;
    }

    private void createPanel() {
        panel = new JPanel();
        editable = new JCheckBox();
        editable.addActionListener(this);

        loadButton = new JButton("Load");
        loadButton.addActionListener(this);

        openBut = new JButton("Open");
        openBut.addActionListener(this);

        panel.add(new JLabel("Open"));
        panel.add(openBut);
        panel.add(loadButton);
        panel.add(new JLabel("editable"));
        panel.add(editable);
    }

    private void createEditorPane() {
        editorPane = new JEditorPane();
        editorPane.setEnabled(false);
    }

    private void createScrollPane() {
        createEditorPane();
        editorscroll = new JScrollPane(editorPane);
        editorscroll.setPreferredSize(new Dimension(700, 450));
    }

    public void createMyPanelJEditorPane() {
        createPanel();
        createScrollPane();
        Box box = Box.createVerticalBox();
        box.add(editorscroll, BorderLayout.CENTER);
        box.add(panel, BorderLayout.SOUTH);
        this.add(box);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e == null) {
            return;
        }
        if (e.getSource() instanceof JCheckBox) {
            editorPane.setEnabled(editable.isSelected());
        } else if (e.getSource() instanceof JButton) {
            JButton buton = (JButton) e.getSource();
            if (buton.getText().equals("Open")) {
                control = frame.getControl();
                control.OpenSelectFileChoocer();
                frame.setControl(control);
            } else if (buton.getText().equals("Load")) {
                control = frame.getControl();
                textResult = control.readSelectFile();
                editorPane.setText(textResult);
            }
        }
    }
}

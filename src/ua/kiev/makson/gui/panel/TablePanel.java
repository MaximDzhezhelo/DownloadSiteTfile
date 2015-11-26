package ua.kiev.makson.gui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import ua.kiev.makson.gui.MyFrame;

public class TablePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private MyFrame frame;
    private JTable table;

    public TablePanel(MyFrame frame) {
        this.frame = frame;
    }

    public void createTablePanel() {
        String[] columnNames = { "one", "two", "three" };
        String[][] cells = { { "///", "---", "111" }, { "df", "asdf", "asdf" },
                { "hg", "fd", "nbvc" } };
        table = new JTable(cells, columnNames);
        table.setAutoCreateRowSorter(true);
        this.add(table, BorderLayout.CENTER);

    }

}

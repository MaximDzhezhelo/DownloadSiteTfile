package ua.kiev.makson.controller;

import java.io.File;
import javax.swing.JFileChooser;

public class SaveWayChoocerEditorPane extends JFileChooser {
    private static final long serialVersionUID = 1L;

    public File createOpenJFileChoocer(File rootDirectory) {
        setCurrentDirectory(rootDirectory);
        setFileSelectionMode(JFileChooser.FILES_ONLY);
        setAcceptAllFileFilterUsed(false);
        int ret = showDialog(null, "Выберите папку");
        if (ret == JFileChooser.APPROVE_OPTION) {
            rootDirectory = getSelectedFile();
        }
        return rootDirectory;
    }

}

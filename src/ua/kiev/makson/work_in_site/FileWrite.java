package ua.kiev.makson.work_in_site;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileWrite {
    private static final Logger LOGGER = Logger.getLogger(FileWrite.class
            .getName());

    public void writeInFile(String docPage, File rootDirectory, String charset,
            String defaultReadName) {
        rootDirectory = new File(rootDirectory, defaultReadName);
        if (!rootDirectory.exists()) {
            try {
                rootDirectory.createNewFile();
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage());
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(rootDirectory), charset));) {
            bw.write(docPage);
            bw.flush();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        LOGGER.log(Level.SEVERE, "write Page of Site in " + charset);
    }
}

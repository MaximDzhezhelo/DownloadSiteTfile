package ua.kiev.makson.work_in_site;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWrite {
    private File rootDirectory;
    private String answer;

    public FileWrite(String answer, File rootDirectory) {
        this.answer = answer;
        this.rootDirectory = rootDirectory;
    }

    public void writeInFile() {
        rootDirectory = new File(rootDirectory, "answer.html");
        if (!rootDirectory.exists()) {
            try {
                rootDirectory.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (DataOutputStream wr = new DataOutputStream(new FileOutputStream(
                rootDirectory))) {
            wr.writeUTF(answer);
            wr.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

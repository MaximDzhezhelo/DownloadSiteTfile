package tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.kiev.makson.work_in_site.FileWrite;

public class FileWriteTest {
    private FileWrite write;
    private static File file;
    private static File answer;

    @BeforeClass
    public static void initData() {
        file = new File("create");
        if (!file.exists()) {
            file.mkdir();
        }
        answer = new File(file, "answer.html");
    }

    @Before
    public void setUp() throws Exception {
        write = new FileWrite("OKKKKKKK", file);
    }

    @Test
    public void testWriteInFile() {
        write.writeInFile();
        assertTrue(answer.exists());
    }

    @AfterClass
    public static void deleteData() {
        answer.delete();
        file.delete();
    }
}

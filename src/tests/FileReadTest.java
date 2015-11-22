package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import ua.kiev.makson.work_in_site.FileRead;

public class FileReadTest {
    private FileRead test;
    private static File rootDirectory;

    @Before
    public void setUpBeforeClass() throws Exception {
        rootDirectory = new File("site.html");
        test = new FileRead("UTF-8");
    }

    @Test
    public void isFileExist() throws IOException {
        if (!rootDirectory.exists()) {
            rootDirectory.createNewFile();
        }
        assertTrue(rootDirectory.exists());
    }

    @Test
    public void isreadFromRootDirectory() {
        String result = test.readFromRootDirectory(rootDirectory);
        assertNotNull(result);
        assertTrue(result.length() == 0);
    }

    @Test(expected = NullPointerException.class)
    public void isreadFromRootDirectoryException() {
        test.readFromRootDirectory(null);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        rootDirectory.delete();
    }
}

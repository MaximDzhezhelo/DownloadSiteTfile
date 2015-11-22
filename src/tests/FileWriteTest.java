package tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.work_in_site.FileWrite;

public class FileWriteTest {
    private FileWrite fileWrite;
    private String docPage;
    private File rootDirectory;
    private String charset;
    private String defaultReadName;
    private ControllerSite controllerSite;
    private static File file;

    public FileWriteTest() {
        controllerSite = new ControllerSite(null, null, null, null);
    }

    @Before
    public void setUp() throws Exception {
        fileWrite = new FileWrite();
        docPage = "asdff_fer-wrv_xv-wervsv";
        rootDirectory = new File("/home/makson/программирование/eclipse/TFile");
        charset = "windows-1251";
        defaultReadName = controllerSite.getDefaultReadName();
    }

    @Test
    public void testWriteInFile() {
        fileWrite.writeInFile(docPage, rootDirectory, charset, defaultReadName);
        assertEquals(defaultReadName, "site.html");
        assertNotEquals(defaultReadName, "xrenb.html");

        file = new File(rootDirectory, defaultReadName);
        assertTrue(file.exists());
        assertFalse(!file.exists());
    }

    @Test(expected = NullPointerException.class)
    public void testExceptionCharsetWriteInFile() throws NullPointerException {
        fileWrite.writeInFile(docPage, rootDirectory, null, defaultReadName);
    }

    @Test(expected = NullPointerException.class)
    public void testExceptiondefaultReadNameWriteInFile()
            throws NullPointerException {
        fileWrite.writeInFile(docPage, rootDirectory, charset, null);
    }

    @Test(expected = NullPointerException.class)
    public void testExceptiondocPageWriteInFile() throws NullPointerException {
        fileWrite.writeInFile(null, rootDirectory, charset, defaultReadName);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        file.delete();
    }
}

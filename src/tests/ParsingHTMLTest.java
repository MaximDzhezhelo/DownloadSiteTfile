package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.kiev.makson.controller.regularexpressionpattern.ParsingHTML;

public class ParsingHTMLTest {
    private static ParsingHTML parsingHTML;
    private static File file;

    @BeforeClass
    public static void initData() {
        parsingHTML = new ParsingHTML();
        file = new File("testFile");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCreateFileTrue() {
        boolean resultCreated = file.exists();
        assertTrue(resultCreated);
    }

    @Test
    public void testCreateFileFalse() {
        File file2 = new File("1");
        boolean resultCreated = file2.exists();
        assertFalse(resultCreated);
    }

    @Test(expected = NullPointerException.class)
    public void testFileNotFound() throws NullPointerException {
        parsingHTML.parseFile(new File("1"));
    }

    @Test
    public void testFileNotFoundMessage() {
        File file2 = new File("1");
        try {
            parsingHTML.parseFile(file2);
            fail();
        } catch (NullPointerException e) {
            assertEquals(e.getMessage(), "File not found");
        }
    }

    @Test(expected = NullPointerException.class)
    public void testFileNull() throws NullPointerException {
        parsingHTML.parseFile(null);
    }

    @Test
    public void testNullFoundMessage() {
        try {
            parsingHTML.parseFile(null);
            fail();
        } catch (NullPointerException e) {
            assertEquals(e.getMessage(), "Look well what file you entered");
        }
    }

    @AfterClass
    public static void deleteData() {
        file.delete();
    }

}

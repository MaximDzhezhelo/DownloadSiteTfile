package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;

import org.apache.http.HttpEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.kiev.makson.work_in_site.FileRead;

public class FileReadTest {
    private FileRead test;
    private File rootDirectory;
    private HttpEntity entity;

    @Before
    public void setUpBeforeClass() throws Exception {
        test = Mockito.mock(FileRead.class);
        rootDirectory = new File("site.html");
        entity = Mockito.mock(HttpEntity.class);
    }

    @Test
    public void isFileExist() {
        assertTrue(rootDirectory.exists());
    }

    @Test
    public void returnStatementFromRootDirectory() {
        when(test.readFromRootDirectory(rootDirectory)).thenReturn("site.html");
        assertEquals(test.readFromRootDirectory(rootDirectory), "site.html");
        assertNotEquals(test.readFromRootDirectory(rootDirectory), "xrenb");
        verify(test, atLeastOnce()).readFromRootDirectory(rootDirectory);
        verify(test, atLeast(2)).readFromRootDirectory(rootDirectory);
        verify(test, never()).readFromRootDirectory(new File("xrenb"));
    }

    @Test
    public void returnStatementFromEntity() {
        when(test.readFromEntity(entity)).thenReturn(anyString());
        // verify(test, atLeastOnce()).readFromEntity(entity);
        assertEquals(test.readFromEntity(entity), anyString());

    }

    @Test
    public void returnStatementFrDirectory() {
        when(test.readFromRootDirectory(rootDirectory)).thenReturn("site.html");
        assertEquals(test.readFromRootDirectory(rootDirectory), "site.html");
        assertEquals(test.readFromRootDirectory(rootDirectory), "site.html");
        assertEquals(test.readFromRootDirectory(rootDirectory), "site.html");
        assertEquals(test.readFromRootDirectory(rootDirectory), "site.html");
        assertEquals(test.readFromRootDirectory(rootDirectory), "site.html");
        verify(test, atLeast(5)).readFromRootDirectory(rootDirectory);
    }
}

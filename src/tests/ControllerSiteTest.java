package tests;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.kiev.makson.controller.controllersite.ControllerSite;

public class ControllerSiteTest {
    private static ControllerSite controllerSite;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        controllerSite = new ControllerSite("df", "dfg", "dfg", new File("dfg"));
    }

    @Test
    public void testDefaultReadName() {
        String name = controllerSite.getDefaultReadName();
        assertTrue(name.equals("site.html"));
    }

    @Test
    public void testColorRegistration() {
        boolean flag = controllerSite.getColorRegistration();
        assertFalse(flag);
        controllerSite.setRegistration(true);
        assertTrue(controllerSite.getColorRegistration());
    }

}

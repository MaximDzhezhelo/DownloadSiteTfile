package tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.kiev.makson.controller.controllersite.ControllerSite;
import ua.kiev.makson.work_in_site.requests.GeneralHttpClient;
import ua.kiev.makson.work_in_site.requests.RequestHelper;
import ua.kiev.makson.work_in_site.requests.authentication.Authentication;

public class AuthenticationTest {
    private static Authentication authentication;
    private static GeneralHttpClient genClient;
    private static ControllerSite controlSite;
    private static RequestHelper requestHelper;
    private static File rootDirectory;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        rootDirectory = new File("Get_Post");
        if (!rootDirectory.exists()) {
            rootDirectory.createNewFile();
        }
        authentication = new Authentication();
        genClient = new GeneralHttpClient();
        controlSite = new ControllerSite("http://tfile.me/", "Mq1aximer",
                "asdasdasd", rootDirectory);
        requestHelper = new RequestHelper();
    }

    @Test
    public void testAuthentication() {
        authentication.startAuthentication(genClient, controlSite,
                requestHelper);
        assertTrue(authentication.getStatusLine() == 302);
        assertTrue(authentication.getRandomTime().getX() == 1);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        rootDirectory.delete();
    }
}

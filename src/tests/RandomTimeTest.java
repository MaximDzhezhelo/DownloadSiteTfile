package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import ua.kiev.makson.timer.RandomTime;

public class RandomTimeTest {
    private RandomTime time;

    @Before
    public void setUpBefore() throws Exception {
        time = new RandomTime();
    }

    @Test
    public void testCheckOne() {
        int t = time.getRandomTime();
        assertNotNull(t);
        assertTrue(t >= 0 && t <= 50);
        assertTrue(time.getX() == 1);
    }

    @Test
    public void testCheckSecond() {
        time = new RandomTime();

        int t = time.getRandomTime();
        t = time.getRandomTime();
        t = time.getRandomTime();
        assertNotNull(t);
        assertTrue(time.getX() == 3);
    }
}

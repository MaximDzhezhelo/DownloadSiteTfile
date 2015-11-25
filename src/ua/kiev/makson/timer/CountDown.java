package ua.kiev.makson.timer;

import java.util.Timer;
import java.util.TimerTask;

public class CountDown extends TimerTask {
    private Timer timer;
    private int count;

    public CountDown(int count, Timer timer) {
        this.count = count;
        this.timer = timer;
    }

    public void remaining() {
        if (count == 0) {
            timer.cancel();
        } else {
            count--;
            timer.schedule(new CountDown(count, timer), 1000);
        }
    }

    @Override
    public void run() {
        remaining();
    }

}

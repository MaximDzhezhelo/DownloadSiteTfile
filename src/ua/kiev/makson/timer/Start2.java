package ua.kiev.makson.timer;

import java.util.Timer;

public class Start2 {
    public static void main(String[] args) {
        System.out.println("12345");
        Timer timer = new Timer();
        RecursiveGetTask rTask = new RecursiveGetTask(0, timer);
        Thread tf = Thread.currentThread();

        System.out.println(tf.getName());
        timer.schedule(rTask, 3000);
        System.out.println("------");

    }

}

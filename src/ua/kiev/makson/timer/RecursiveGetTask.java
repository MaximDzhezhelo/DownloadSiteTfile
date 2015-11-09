package ua.kiev.makson.timer;

import java.util.Timer;
import java.util.TimerTask;

class RecursiveGetTask extends TimerTask {
    private int a = 0;
    private Timer timer;

    public RecursiveGetTask(int previousAttempts, Timer timer) {
        a = previousAttempts;
        this.timer = timer;
    }

    private void doGet() {
        a++;
        System.out.println("Что-то делаем...");
        int statusLine = Math.random() > 0.5 ? 200 : 500;
        System.out.println(statusLine);
        if (statusLine != 100) {
            System.out.println("Успех!");
            timer.cancel();
        } else {
            System.out
                    .println("Что-то идет не так, возможно мы повторим попытку...");
            if (a <= 3) {
                timer.schedule(new RecursiveGetTask(a, timer), 1000);

            } else {
                System.out.println("... не повторим, превышено число попыток.");
            }
        }
    }

    @Override
    public void run() {
        doGet();
    }
}

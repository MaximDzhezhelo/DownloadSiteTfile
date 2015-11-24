package ua.kiev.makson.timer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Start2 {

    public static void main(String[] args) throws InterruptedException,
            ExecutionException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Future1 task1 = new Future1();
        Future2 task2 = new Future2();
        RandomTime random = new RandomTime();
        int z = random.getRandomTime();
        System.out.println(z);
        ScheduledFuture<?> tast1 = executor
                .schedule(task1, z, TimeUnit.SECONDS);

        int x = (Integer) tast1.get();
        System.out.println(x);
        if (x == 2) {
            ScheduledFuture<?> tast2 = executor.schedule(task2, 4,
                    TimeUnit.SECONDS);
            System.out.println(tast2.get());
        }

        System.out.println("03");
        System.out.println("13");

        System.out.println("23");
        System.out.println("23");
        System.out.println("23");
        System.out.println("23");

        executor.shutdown();
    }
}

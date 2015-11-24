package ua.kiev.makson.timer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Start2 {
    private static ScheduledFuture<?> tast1;
    private static ScheduledExecutorService executor = Executors
            .newScheduledThreadPool(1);
    private static Future1 task1 = new Future1();
    private static RandomTime random = new RandomTime();
    static int z = random.getRandomTime();
    static int c = 0;

    public static void main(String[] args) throws InterruptedException,
            ExecutionException {

        Future2 task2 = new Future2();

        System.out.println(z);
        z = 1;
        tast1 = executor.schedule(task1, z, TimeUnit.SECONDS);

        int x = (Integer) tast1.get();
        System.out.println(x);
        c++;

        while (!(x == 2)) {
            s();
        }
        System.out.println("x = 0  " + x);
        ScheduledFuture<?> tast2 = executor
                .schedule(task2, 4, TimeUnit.SECONDS);
        System.out.println(tast2.get());
        System.out.println("executor.shutdown()");
        executor.shutdown();
    }

    public static int s() throws InterruptedException, ExecutionException {
        System.out.println("failed");
        tast1 = executor.schedule(task1, z, TimeUnit.SECONDS);
        return (Integer) tast1.get();

    }
}

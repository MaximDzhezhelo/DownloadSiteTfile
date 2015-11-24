package ua.kiev.makson.timer;

import java.util.concurrent.Callable;

public class Future2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
System.out.println("run future2");
        return 4;
    }

}

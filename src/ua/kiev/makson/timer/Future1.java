package ua.kiev.makson.timer;

import java.util.concurrent.Callable;

public class Future1 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        System.out.println("run future1");

        return 2;
    }
}

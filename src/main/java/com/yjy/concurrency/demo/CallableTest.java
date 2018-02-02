package com.yjy.concurrency.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask也可以用于闭锁
 */
public class CallableTest {

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();

        FutureTask<Integer> futureTask = new FutureTask<Integer>(td);
        new Thread(futureTask).start();


        try {
            Integer sum = futureTask.get();
            System.out.println(sum);
            System.out.println("---------------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class ThreadDemo implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = 1; i <= 10000; i++) {
                sum += i;
            }
            return sum;
        }
    }
}

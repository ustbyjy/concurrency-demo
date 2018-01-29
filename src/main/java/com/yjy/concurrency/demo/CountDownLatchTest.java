package com.yjy.concurrency.demo;

import com.yjy.concurrency.util.SleepUtils;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        Thread parser1 = new Thread(new Runnable() {
            @Override
            public void run() {
                SleepUtils.second(1);
                System.out.println("parser1 finish");
                c.countDown();
            }
        });
        Thread parser2 = new Thread(new Runnable() {
            @Override
            public void run() {
                SleepUtils.second(1);
                System.out.println("parser2 finish");
                c.countDown();
            }
        });

        parser1.start();
        parser2.start();

        c.await();

        System.out.println("all parser finish");
    }
}

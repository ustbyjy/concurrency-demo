package com.yjy.concurrency.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VolatileDemo {
    private static Logger logger = LoggerFactory.getLogger(VolatileDemo.class);

    private volatile static int count = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    count++;
                    logger.info("Thread 1：count={}", count);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    logger.info("Thread 2：count={}", count);
                }
            }
        });

        t1.start();
        t2.start();
    }

}

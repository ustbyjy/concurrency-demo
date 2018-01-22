package com.yjy.concurrency.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadLockDemo {
    private static Logger logger = LoggerFactory.getLogger(DeadLockDemo.class);

    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        logger.info("1");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (A) {
                        logger.info("2");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }

}

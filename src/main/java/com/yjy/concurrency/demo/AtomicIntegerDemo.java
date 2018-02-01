package com.yjy.concurrency.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    private static Logger logger = LoggerFactory.getLogger(AtomicIntegerDemo.class);

    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }
    }

    static class AtomicDemo implements Runnable {
        private AtomicInteger serialNumber = new AtomicInteger(0);
//        private int serialNumber = 0;

        @Override
        public void run() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            logger.info(Thread.currentThread().getName() + ":" + getSerialNumber());
        }

        public int getSerialNumber() {
            return serialNumber.getAndIncrement();
        }
    }
}


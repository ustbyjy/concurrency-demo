package com.yjy.concurrency.demo;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch：闭锁
 */
public class CountDownLatchTest2 {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(5);

        LatchDemo ld = new LatchDemo(latch);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            new Thread(ld).start();
        }

        latch.await();
        long end = System.currentTimeMillis();

        System.out.println("Cost: " + (end - start));
    }

    static class LatchDemo implements Runnable {
        private CountDownLatch latch;

        LatchDemo(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            synchronized (this) {
                try {
                    for (int i = 0; i < 5000; i++) {
                        if (i % 2 == 0) {
                            System.out.println(i);
                        }
                    }
                } finally {
                    latch.countDown();
                }
            }
        }
    }

}

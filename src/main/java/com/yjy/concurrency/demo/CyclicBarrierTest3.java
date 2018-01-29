package com.yjy.concurrency.demo;

import java.util.concurrent.CyclicBarrier;

/**
 * CountDownLatch的计数器只能使用一次，CyclicBarrier的计数器可以使用reset方法重置
 */
public class CyclicBarrierTest3 {
    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.interrupt();

        try {
            c.await();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(c.isBroken());
        }
    }


}

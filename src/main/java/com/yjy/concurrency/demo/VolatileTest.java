package com.yjy.concurrency.demo;

public class VolatileTest {

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();
        while (true) {
            if (td.isFlag()) {
                System.out.println("----------------------------------");
                break;
            }
        }
    }

    static class ThreadDemo implements Runnable {
        //    private volatile boolean flag = false;
        private boolean flag = false;

        @Override
        public void run() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }
            flag = true;
            System.out.println("flag=" + flag);
        }

        public boolean isFlag() {
            return flag;
        }
    }
}



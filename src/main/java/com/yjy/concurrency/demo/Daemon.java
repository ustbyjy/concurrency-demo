package com.yjy.concurrency.demo;

import com.yjy.concurrency.util.SleepUtils;

/**
 * Daemon 线程是一 种支持型线程，当Java虚拟机不存在非Daemon线程的时候，Java虚拟机将会退出。
 */
public class Daemon {

    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                SleepUtils.second(10);
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }

}

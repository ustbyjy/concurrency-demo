package com.yjy.concurrency.demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BankWaterService implements Runnable {
    // 创建4个屏障
    private CyclicBarrier c = new CyclicBarrier(4, this);
    // 只启动4个线程
    private Executor executor = Executors.newFixedThreadPool(4);
    // 保存每个sheet计算出的银流结果
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        BankWaterService bankWaterCount = new BankWaterService();
        bankWaterCount.count();
    }

    private void count() {
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    // 计算当前sheet的银流数据，计算代码省略
                    sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                    // 银流计算完成，插入一个屏障
                    try {
                        c.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        int result = 0;
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }
        sheetBankWaterCount.put("result", result);
        System.out.println(result);
    }
}

package com.yjy.concurrency.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache {
    static Map<String, Object> cacheMap = new HashMap<String, Object>();
    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = reentrantReadWriteLock.readLock();
    static Lock writeLock = reentrantReadWriteLock.writeLock();

    // 获取一个key对应的value
    public static final Object get(String key) {
        readLock.lock();
        try {
            return cacheMap.get(key);
        } finally {
            readLock.unlock();
        }
    }

    // 设置key对应的value，并返回旧的value
    public static final Object set(String key, Object value) {
        writeLock.lock();
        try {
            return cacheMap.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    // 清空所有的内容
    public static final void clear() {
        writeLock.lock();
        try {
            cacheMap.clear();
        } finally {
            writeLock.unlock();
        }
    }

}

package com.imooc.concurrency.example.atomic;

import com.imooc.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicIntegerFieldUpdater核心是想原子性的更新一个类的实例的一个字段
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    /**
     * 必须是volatile修饰而且不能是static才可以
     */
    @Getter
    public volatile int count = 100;


    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();

        if(updater.compareAndSet(example5,100,120)){
            log.info("update success 1,{}",example5.getCount());
        }
        if(updater.compareAndSet(example5,100,120)){
            log.info("update success 2,{}",example5.getCount());
        }else {
            log.info("update failed,{}",example5.getCount());
        }
    }
}

package com.imooc.concurrency.example.sync;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author zty
 * @Date 2020/3/20 下午6:32
 * @Description:
 */
@Slf4j
public class SynchronizedExample2 {

    public static void test1(int j){
        //修饰一个代码块 作用范围 大括号里的
        synchronized (SynchronizedExample2.class){
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} - {}",j,i);
            }
        }
    }
    public static synchronized void test2(int j ){
        for (int i = 0; i < 10; i++) {
            log.info("test2 {} - {}",j,i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()-> {
            example1.test2(1);
        });
        executorService.execute(()-> {
            example2.test2(2);
        });
    }
}

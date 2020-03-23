package com.imooc.concurrency.syncContainer;

import com.imooc.concurrency.annoations.NotThreadSafe;
import com.imooc.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author zty
 * @date 2020/3/21 下午9:52
 * @description:
 */
@Slf4j
@ThreadSafe
public class HashTableExample {
    //请求总数
    public static int cilentTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;


    private static Hashtable<Object, Object> map = new Hashtable<>();
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);

        //计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(cilentTotal);
        for (int i = 0; i < cilentTotal; i++){
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}",map.size());
    }

    private static void update(int i){
        map.put(i,i);
    }
}

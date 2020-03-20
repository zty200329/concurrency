package com.imooc.concurrency;

import com.imooc.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author zty
 * @Date 2020/3/8 下午9:07
 * @Description: 线程不安全
 */

@Slf4j
@NotThreadSafe
public class ConcurrencyTest {
    //请求总数
    public static int cilentTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    //计数
    public static int count = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);

        //计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(cilentTotal);
        for (int i = 0; i < cilentTotal; i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count);
    }

    private static void add(){
        count++;
    }

}

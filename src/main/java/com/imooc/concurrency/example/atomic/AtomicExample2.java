package com.imooc.concurrency.example.atomic;

import com.imooc.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author zty
 * @Date 2020/3/9 下午6:45
 * @Description: AtomicLong 与LongAdder很像 jkd8新增
 * LongAdder优点:AtomicInteger 是在一个死循环里不断尝试 如果竞争激烈,修改失败会增加
 *
 * 思想:
 * 拆分分开加
 *
 * 缺点: 统计时 如果有并发更新 统计时会有误差
 *
 * 优先使用LongAdder
 * 在并发量小的时候使用actomic还是不错的
 */
@Slf4j
@ThreadSafe
public class AtomicExample2 {
    public static int cilentTotal = 5000;

    public static int threadTotal = 200;

    public static LongAdder count = new LongAdder();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);

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
        count.increment();
    }
}

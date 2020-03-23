package com.imooc.concurrency.singleton;

import com.imooc.concurrency.annoations.NotRecommend;
import com.imooc.concurrency.annoations.ThreadSafe;

/**
 * @author zty
 * @date 2020/3/21 下午3:59
 * @description: 懒汉模式
 * 单例实例只在第一次使用时进行  创建
 */
@ThreadSafe
@NotRecommend
public class SingletonExample1 {

    //私有构造函数
    private SingletonExample1(){

    }
    //单例对象
    private static SingletonExample1 instance = null;

    //静态的工厂方法

    /**
     * synchronized影响性能 不推荐
     * @return
     */
    public static synchronized SingletonExample1 getInstance(){
        if(instance == null){
            instance =new SingletonExample1();
        }
        return instance;
    }
}

package com.imooc.concurrency.singleton;

import com.imooc.concurrency.annoations.NotRecommend;
import com.imooc.concurrency.annoations.NotThreadSafe;
import com.imooc.concurrency.annoations.ThreadSafe;

/**
 * @author zty
 * @date 2020/3/21 下午3:59
 * @description: 懒汉模式 ->双重同步锁单例模式
 * 单例实例只在第一次使用时进行  创建
 */
@NotThreadSafe
public class SingletonExample4 {

    //私有构造函数
    private SingletonExample4(){
    }
    //1.memory = allocate()  分配对象的内存空间
    //2.ctorInstance()初始化对象
    //3.instance = memory 设置instance指向刚分配的内存

    //JVM和CPU发生指令重排

    //1. memory = allocate()  分配对象的内存空间
    //3.instance = memory 设置instance指向刚分配的内存
    //2.ctorInstance()初始化对象
    //单例对象
    private static SingletonExample4 instance = null;

    //静态的工厂方法
    public static synchronized SingletonExample4 getInstance(){
        if(instance == null){//双重检测机制
            synchronized (SingletonExample4.class) {//同步锁
                if(instance == null) {
                    instance = new SingletonExample4(); // A-3
                }
            }
        }
        return instance;
    }
}

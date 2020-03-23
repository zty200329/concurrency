package com.imooc.concurrency.singleton;

import com.imooc.concurrency.annoations.ThreadSafe;

/**
 * @author zty
 * @date 2020/3/21 下午3:59
 * @description: 饿汉模式
 * 单例实例在类装载使用时进行创建
 */

@ThreadSafe
public class SingletonExample2 {

    //私有构造函数
    private SingletonExample2(){

    }
    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    //静态的工厂方法
    public static SingletonExample2 getInstance(){
        return instance;
    }
}

package com.imooc.concurrency.singleton;

import com.imooc.concurrency.annoations.Recommend;
import com.imooc.concurrency.annoations.ThreadSafe;

/**
 * @author zty
 * @date 2020/3/21 下午4:25
 * @description:
 */

@ThreadSafe
@Recommend
public class SingletonExample7 {
    //私有构造函数
    private SingletonExample7(){
    }
    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getSingleton();
    }
    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        //JVM保证这种方法绝对只调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }
        public SingletonExample7 getSingleton(){
            return singleton;
        }
    }
}

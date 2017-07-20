package com.dk.juc.concurrent.lockfree;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA
 * BeanManager2
 *
 * @author dk
 * @date 2017/7/20 18:12
 */
public class BeanManager2 {

    private ConcurrentHashMap<String,Object> map = new ConcurrentHashMap();

    public Object getBean(String key){
            Object bean = map.get(key);
            if(bean == null){
                map.putIfAbsent(key,bean);//putIfAbsent
                bean = map.get(key);
            }
            return bean;
    }
}

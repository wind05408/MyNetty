package com.dk.juc.concurrent.lockfree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * BeanManager1
 *
 * @author dk
 * @date 2017/7/20 18:09
 */
public class BeanManager1 {
    private Map<String,Object> map = new HashMap();

    public Object getBean(String key){
        synchronized (map){
            Object bean = map.get(key);
            if(bean == null){
                map.put(key,bean);
                bean = map.get(key);
            }
            return bean;
        }
    }
}

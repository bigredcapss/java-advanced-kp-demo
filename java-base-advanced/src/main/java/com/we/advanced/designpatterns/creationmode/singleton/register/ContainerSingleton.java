package com.we.advanced.designpatterns.creationmode.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author we
 * @date 2021-08-13 18:38
 **/
public class ContainerSingleton {
    private ContainerSingleton() {
    }

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();

    public static Object getBean(String className) {
        synchronized (ioc) {
            if (!ioc.containsKey(className)) {
                Object o = null;
                try {
                    o = Class.forName(className).newInstance();
                    ioc.put(className, o);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return o;
            } else {
                return ioc.get(className);
            }
        }
    }
}

package com.we.advanced.designpatterns.creationmode.singleton.register;

import java.lang.reflect.Constructor;

/**
 * @author we
 * @date 2021-08-13 18:38
 **/
public class ContainerSingletonTest {
    public static void main(String[] args) {
        try {
            Class<?> clazz = ContainerSingleton.class;
            Constructor constructors = clazz.getDeclaredConstructor(null);

            constructors.setAccessible(true);
            Object o1 = constructors.newInstance();
            Object o2 = constructors.newInstance();

            System.out.println("o1:"+o1);
            System.out.println("o2:"+o2);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

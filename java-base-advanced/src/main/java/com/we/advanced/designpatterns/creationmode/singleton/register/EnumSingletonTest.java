package com.we.advanced.designpatterns.creationmode.singleton.register;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author we
 * @date 2021-08-13 16:59
 **/
public class EnumSingletonTest {
    public static void main(String[] args) {
        try {
            EnumSingleton instance1 = EnumSingleton.getInstance();
            EnumSingleton instance2 = null;
            instance1.setData(new Object());

            FileOutputStream fos = new FileOutputStream("EnumSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(instance1);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("EnumSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            instance2 = (EnumSingleton) ois.readObject();
            ois.close();

            System.out.println(instance1.getData());
            System.out.println(instance2.getData());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

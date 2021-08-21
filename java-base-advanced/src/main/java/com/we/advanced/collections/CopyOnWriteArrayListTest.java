package com.we.advanced.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteList解决Java集合的fail-fast问题
 * @author we
 * @date 2021-08-20 09:51
 **/
public class CopyOnWriteArrayListTest {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> collection = new CopyOnWriteArrayList<>(Arrays.asList("we","nb","lh"));
        List<String> list = new ArrayList<>(Arrays.asList("123","234"));
        /*for (String key : collection) {

            System.out.println(key);
            collection.remove(1);
        }
        for (String key : list) {
            list.add("345");
            System.out.println(key);
        }*/





    }

}

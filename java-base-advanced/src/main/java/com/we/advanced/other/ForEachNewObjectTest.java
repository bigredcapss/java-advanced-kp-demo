package com.we.advanced.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * for循环创建对象测试
 * @author we
 * @date 2021-05-13 17:30
 **/
public class ForEachNewObjectTest {
    public static void main(String[] args) {
        List<User> records = new ArrayList<>();
        List<String> addresss = new ArrayList<>(Arrays.asList("嘉兴","杭州","上海"));
        User user = new User();
        for (String address:addresss) {
            user.setAddress(address);
            user.setAge(13);
            user.setName("小明");
            records.add(user);
        }

        for (User record :records) {
            System.out.println("name:{},age{},address:{}"+record.getName()+record.getAge()+record.getAddress());
        }
    }
}

package com.we.advanced.toobject;


/**
 * 不可变类与可变类
 * @author we
 * @date 2021-08-18 14:55
 **/
public class Person {
    /**
     * final修饰引用类型的含义:final修饰引用类型的变量时，仅表示这个引用类型变量不可被修改；但引用类型变量所指向的对象的属性值依然可以改变；
     * 思考：当创建不可变类时，如果它包含的成员变量的类型是可变的，那么其对象的成员变量的值依然是可以改变的，这个地方要注意。而我们平常创建的大部分
     * 类都是可变类。
     */
    private final Name name;

    public Person(Name name){
        this.name = name;
    }

    public Name getName(){
        return name;
    }

    public static void main(String[] args) {
        Name n = new Name("w","e");
        Person person = new Person(n);
        System.out.println(person.getName().getFirstName());
        n.setFirstName("q");
        System.out.println(person.getName().getFirstName());
    }



}
class Name{
    private String firstName;
    private String lastName;
    public Name(){

    }
    public Name(String firstName,String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

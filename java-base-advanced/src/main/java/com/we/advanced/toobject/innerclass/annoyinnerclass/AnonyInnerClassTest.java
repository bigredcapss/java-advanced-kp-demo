package com.we.advanced.toobject.innerclass.annoyinnerclass;

/**
 * 匿名内部类再理解
 * @author we
 * @date 2021-08-18 10:08
 * @description
 * ①从重构的角度来看，匿名内部类可以让你的代码更加简洁;
 *
 *
 **/
public class AnonyInnerClassTest {

    /**
     * 计算一个方法执行了多少秒（普通写法）
     * 思考: 如果我也想要统计其它方法的执行时间，我是不是要把除去业务的几行代码复制过去呢？（傻）
     * 能不能业务代码抽象成一个参数呢？有点难，但是可以抽象成一个类或者接口。
     */
    public static void test(){
        long start = System.currentTimeMillis();

        // 执行打印的业务逻辑
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    /**
     * 优化test方法
     */
    public static void optimize_test(MyService myService){
        long start = System.currentTimeMillis();

        // 执行打印的业务逻辑
        myService.invoke();

        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    public static void main(String[] args) {
        optimize_test(new MyService(){
            @Override
            public void invoke(){
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                }
            }
        });
    }

    /**
     * 思考：lambda表达式优化了匿名内部类的语法，但是lambda表达式只能替换只有一个抽象方法的接口，如果我在MyService中又
     * 添加了一个抽象方法invoke2()编译是不通过的。
     */


}

package com.we.advanced.toobject;

import java.lang.ref.WeakReference;

/**
 * 虚引用
 * 下面代码中，如果类B不是弱引用类A的话，执行main方法可能出现内存泄漏问题，因为类B依赖于A;
 * 静态内部类中经常会使用弱引用。
 * @author we
 * @date 2021-08-18 16:34
 **/
public class Reference {
    public static void main(String[] args) {
        A a = new A();
        B b = new B(a);
        a = null;
        System.gc();
        System.out.println(b.getA());



    }
}
class A {

}
class B {
    WeakReference<A> weakReference;

    public B(A a){
        weakReference = new WeakReference<>(a);
    }

    public A getA(){
        return weakReference.get();
    }
}

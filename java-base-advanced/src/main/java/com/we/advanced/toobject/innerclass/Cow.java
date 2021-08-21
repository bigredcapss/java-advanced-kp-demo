package com.we.advanced.toobject.innerclass;


/**
 * 普通内部类
 * @author we
 * @date 2021-08-18 10:38
 **/
public class Cow {

    private double weight;

    public Cow(){ }

    public Cow(double weight){
        this.weight = weight;
    }

    /**
     * 普通内部类
     */
    private class CowLeg{

        private double length;
        private String color;

        public CowLeg(){}

        public CowLeg(double length,String color){
            this.length = length;
            this.color = color;
        }

        public double getLength() {
            return length;
        }

        public void setLength(double length) {
            this.length = length;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void info(){
            System.out.println("Cow的颜色："+color+",身高："+length);
            System.out.println("牛腿所在的奶牛重："+weight);
        }
    }

    public void test(){
        CowLeg cowLeg = new CowLeg(1.12,"花花牛");
        cowLeg.info();
    }



    public static void main(String[] args) {
        Cow cow = new Cow(380.23);
        cow.test();




    }
}

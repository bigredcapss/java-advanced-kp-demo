package com.we.advanced.designpatterns.creationmode.singleton.register;

/**
 * @author we
 * @date 2021-08-13 16:57
 **/
public enum EnumSingleton {
    INSTANCE;

    private Object data;

    public Object getData(){
        return data;
    }

    public void setData(Object data){
        this.data = data;
    }

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }

}

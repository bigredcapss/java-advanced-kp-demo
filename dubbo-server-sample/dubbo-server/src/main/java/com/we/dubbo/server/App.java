package com.we.dubbo.server;

import org.apache.dubbo.container.Main;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // 调用dubbo的main方法启动服务
        Main.main(args);
        System.out.println( "Hello World!" );
    }
}

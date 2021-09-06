package com.we.dubbo;

import com.we.dubbo.server.ILoginService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/application.xml");
        ILoginService loginService = context.getBean(ILoginService.class);
        System.out.println(loginService.login("admin", "admin"));
    }
}

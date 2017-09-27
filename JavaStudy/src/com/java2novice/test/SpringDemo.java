package com.java2novice.test;

/**
 * Created by tzh on 1/25/17.
 */


import com.java2novice.beans.SpringFirstTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo {

    public static void main(String a[]){
        String confFile = "com/java2novice/applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(confFile);
        SpringFirstTest sft = (SpringFirstTest) context.getBean("springTest");
        sft.testMe();
    }
}
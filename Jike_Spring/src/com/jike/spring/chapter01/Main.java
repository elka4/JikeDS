package com.jike.spring.chapter01;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class Main {

    public static void main(String[] args) {

    	//利用FileSystemResource读取配置文件
        Resource r = new FileSystemResource("Jike_Spring/src/com/jike/spring/chapter01/helloMessage.xml");

        //利用XmlBeanFactory来加载配置文件，启动IOC容器
        BeanFactory f = new XmlBeanFactory(r);

        //从IOC容器中获取Person类的实例
        Person person = (Person) f.getBean("person");

        //person实例向大家输出问候信息
        String s = person.sayHello();

        //在系统控制台中打印问候信息，由于在这里配置文件中配置是HelloWorld的实例，
        //所以，在这里打印的是字符串：HelloWorld
        System.out.println("The Person is currently saying: "+s);
    }

}

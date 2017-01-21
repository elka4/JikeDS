package com.vince.ref;

/**
 * Created by tzh on 1/20/17.
 */
public class Reflection {
    public static void main(String[] args) {
        Person p1 = new Person("XiaoBai", 18, 'M');
        Person p2 = new Person("XiaoHei", 19, 'F');

        //一个Class对象代表着一份字节码，相同类型的对象的字节码对象是同一个
        //只加载一次

        //创建Class对象的方式一
        Class personClass1 = p1.getClass();
        Class personClass2 = p2.getClass();
        System.out.println(personClass1 == personClass2);


        //创建Class对象的方式二
        Person p3 = new Person("XiaoBai", 18, 'M');

        Class personClass3 = Person.class;
        System.out.println(personClass1 == personClass3);

        //int.class
        //void.class

        //创建Class对象的方式三

        Class personClass4 = null;
        try {
            personClass4 = Class.forName("com.vince.ref.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(personClass1 = personClass4);


    }
}










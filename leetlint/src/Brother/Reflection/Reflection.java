package Brother.Reflection;

import java.lang.reflect.Constructor;

/**
 * Created by tzh on 1/20/17.
 */
public class Reflection {
    public static void main(String[] args)  {
        Person p1 = new Person("XiaoBai", 18, 'M');
        Person p2 = new Person("XiaoHei", 19, 'F');

        //一个Class对象代表着一份字节码，相同类型的对象的字节码对象是同一个
        //只加载一次

        //创建Class对象的方式一
        Class personClass1 = p1.getClass();
        Class personClass2 = p2.getClass();
        System.out.println(personClass1 == personClass2);


        //创建Class对象的方式二

        Class personClass3 = Person.class;
        System.out.println(personClass1 == personClass3);

        //int.class
        //void.class

        //创建Class对象的方式三

        Class personClass4 = null;
        try {
            personClass4 = Class.forName("Brother.Reflection.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(personClass4 == personClass1);


        System.out.println("==============使用Class类进行对象的实例化操作============");


        System.out.println("===========调用无参构造方法================");
        //调用次方法时要注意，如果类中没有无参构造方法，将会报异常InstantiationException
        try {
            Person p3 = (Person)personClass4.newInstance();
            System.out.println(p3);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("===========调用有参构造方法================");

        Constructor[] cs = personClass4.getConstructors();//获取当前类的所有构造方法

        for (Constructor c :cs  ) {
            System.out.println(c.getName());

            Class[] parasTypes = c.getParameterTypes();//得到类型
            //System.out.println(parasTypes);

            for (Class class1 : parasTypes      ) {
                System.out.println(class1);

            }

        }

        try {
            //获取指点参数类型的构造方法
            Constructor c1 = personClass4.getConstructor(String.class, int.class, char.class);
            Person p4 = (Person)c1.newInstance("小花", 17, '女');
            System.out.println(p4);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("================获得类信息===============");
        System.out.println(personClass4.getPackage());



    }//main



}










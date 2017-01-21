package Brother.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
        Person p4 = null;
        try {
            //获取指点参数类型的构造方法
            Constructor c1 = personClass4.getConstructor(String.class, int.class, char.class);
            p4 = (Person)c1.newInstance("小花", 17, '女');
            System.out.println(p4);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("================获得类信息===============");
        System.out.println("包信息" + personClass4.getPackage());
        System.out.println("类名" + personClass4.getName());


        System.out.println("================获得方法信息===============");

        //获得全部方法，包括公共方法
        Method[] m1 = personClass4.getMethods();
        System.out.println("全部方法, 不包括私有方法"   );
        for (Method m : m1) {

           // System.out.println(m);
            System.out.println("方法名" + m.getName() + "访问修饰符" + Modifier.toString(m.getModifiers()));
            //System.out.println(m.getParameterTypes());
        }

        System.out.println("自定义方法, 不包括继承的方法，包括私有方法"   );
        Method[] m2 = personClass4.getDeclaredMethods();
        for (Method m : m2) {
            System.out.println(m);
        }

        System.out.println("================获属性信息===============");
        System.out.println("获取非私有属性");

        Field[] fs1 = personClass4.getFields();
        for (Field f :fs1            ) {
            System.out.println(f);

        }
        System.out.println("获取全部属性");
        Field[] fs2 = personClass4.getDeclaredFields();
        for (Field f :fs2            ) {
            System.out.println(f);

        }

        //349
        System.out.println("=========调用方法和属性=====");
        System.out.println("=========调用方法=====");

        try {
            Method  setNameMethod = personClass4.getMethod("setName", String.class);
            //调用方法
            setNameMethod.invoke(p4, "花花");//p4.setName("花花")；
            System.out.println(p4);
            Method sayMethod = personClass4.getDeclaredMethod("say");//不能接触到私有方法
            //sayMethod.invoke(p4);
            sayMethod.setAccessible(true);
            sayMethod.invoke(p4);


        } catch (Exception e) {
            e.printStackTrace();
        }

        //350
        System.out.println("=========调用属性=====");
        try {
            Field ageField = personClass4.getDeclaredField("age");
            ageField.setAccessible(true);//忽略检查访问修饰符
            System.out.println(ageField.get(p4));//获取p4的age属性
            ageField.set(p4,18);//给p4对象的age属性赋值
            System.out.println(p4);


        } catch (Exception e) {
            e.printStackTrace();
        }





    }//main





}










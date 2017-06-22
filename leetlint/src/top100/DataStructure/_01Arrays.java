package top100.DataStructure;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by tzh on 1/18/17.
 */
public class _01Arrays {
/*    public static void main(String[] args) {
        int[] numArray = new int[10];
        for (int i = 0; i < 10; ++i) {
            numArray[i] = i;
        }
        for (int i = 0; i < 10; ++i) {
            System.out.print(numArray[i] + " ");
        }
        System.out.println("");
    }*/

    public class Person {
        String name;
        int age;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public class Student implements Cloneable {
        String name;
        int age;
        Student(String name,int age) {
            this.name=name;
            this.age=age;
        }
        public Object clone() {
            Object o=null;
            try
            {
                o=(Student)super.clone();//Object 中的clone()识别出你要复制的是哪一个对象。
            }
            catch(CloneNotSupportedException e)
            {
                System.out.println(e.toString());
            }
            return o;
        }
    }

    @Test
    public void test01() {
        int[] numArray = new int[10];
        for (int i = 0; i < 10; ++i) {
            numArray[i] = i;
        }
        for (int i = 0; i < 10; ++i) {
            System.out.print(numArray[i] + " ");
        }
        System.out.println("");
    }

    @Test
    public void test02() {
        Person[] p1 = new Person[1];
        p1[0] = new Person("Tom", 13);
        Person[] p2 = new Person[1];
        p2 = Arrays.copyOf(p1, 1);

        System.out.println(p1[0]);
        System.out.println(p2[0]);

        System.out.println(p1[0].name);
        System.out.println(p2[0].name);

        p1[0].name = "Mary";

        System.out.println(p1[0].name);
        System.out.println(p2[0].name);

    }


    @Test
    public void test03(){
        String[] a1 = new String[1];
        a1[0] = "Tom";
        String[] a2 = a1.clone();
        System.out.println(a1);
        System.out.println(a2);

        System.out.println(a1[0]);
        System.out.println(a2[0]);


        a1[0] = "Mary";
        System.out.println(a1[0]); //"Mary"
        System.out.println(a2[0]); //"Tom"

    }

    @Test
    public void test04() {
        Person[] p1 = new Person[1];
        p1[0] = new Person("Tom", 13);
        //Person[] p2 = new Person[1];
        Person[] p2 = p1.clone();
        Collection c;


        System.out.println(p1[0]);
        System.out.println(p2[0]);


        System.out.println(p1[0].name);
        System.out.println(p2[0].name);

        p2[0].name = "Mary";

        System.out.println(p1[0].name);
        System.out.println(p2[0].name);



    }

    @Test
    public void testClone01() {
        Student s1=new Student("zhangsan",18);
        Student s2=(Student)s1.clone();
        System.out.println(s1);
        System.out.println(s2);

        System.out.println("name="+s1.name+","+"age="+s1.age);//修改学生2后，不影响学生1的值。
        System.out.println("name="+s2.name+","+"age="+s2.age);//修改学生2后，不影响学生1的值。
        s2.name="lisi";
        s2.age=20;
        System.out.println("name="+s1.name+","+"age="+s1.age);//修改学生2后，不影响学生1的值。
        System.out.println("name="+s2.name+","+"age="+s2.age);//修改学生2后，不影响学生1的值。

    }



}

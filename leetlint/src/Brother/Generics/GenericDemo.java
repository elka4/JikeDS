package Brother.Generics;

import java.util.*;
import java.util.Map.Entry;

/**
 * Created by tzh on 1/21/17.
 */
public class GenericDemo {

    public static void main(String[] args){
        ChildrenG<Integer> c1 = new ChildrenG<>("小小白", 3);
        ChildrenG<Float> c2 = new ChildrenG("小小黑", 1.6f);
        ChildrenG<String> c3 = new ChildrenG<String>("小小黄", "二");

        int age1 = c1.getAge();
        System.out.println(c1.getName() + "的年龄是"+ c1);

        //java.lang.ClassCastException
        float age2 = c2.getAge();
        System.out.println(c2.getName() + "的年龄是"+ c2);

        String age3 = c3.getAge();
        System.out.println(c3.getName() + "的年龄是"+ c3);

       Collection<String> c01 = new Vector();//前面写
       Collection c02 = new Vector<String>();//后面写
        //两边都写
        //不能写成不一样

       Vector v1 = new Vector<Object>();
       Vector<String> v2 = v1;

       Vector v3 = new Vector<String>();
       Vector<Object> v4 = v3;


        //定义数组不能使用泛型
        //ChildrenG<String>[] cs = new ChildrenG<String>[10]; //不行

        //在构造方法上使用泛型
        //使用时没有指定泛型的话，泛型擦除，使用Object接受
        ChildrenG childrenG = new ChildrenG();

        //通配符

        ChildrenG<Integer> c7 = new ChildrenG<Integer>();
        ChildrenG<String> c8 = new ChildrenG<String>();
        //c8 = c7;//错误的赋值方式， 参数化类型不同， 两个对象不能相互赋值。

        //通配符
        //？表示的是可以接受任意的泛型类型，但是只是接受输出，并不能修改。

        print(c7);
        print(c1);
        print(c2);


        //泛型的上限 ？extends类
        //泛型的下限 ？super类
        System.out.println("======上限下限=====");
        ChildrenGG c9 = new ChildrenGG<Number>();
        //ChildrenGG<String> c10 = new ChildrenGG<Number>();
        //ChildrenGG<Integer> c11 = new ChildrenGG<Number>();
        fun(c3);

        //使用泛型方法
        String[] names = {"小白", "凤凤","空空" };
        names = func(names, 0, 2);
        for (String name : names  ) {
            System.out.println(name);
        }

        //泛型的嵌套使用
        Map map = new HashMap();
        Set<Entry<Integer, String>> entries = map.entrySet();


    }
    /*
    使用通配符？定义参数
    只能输出不能修改
     */

    public static  void print(ChildrenG<?> c) {
        //c.setAge(10);//使用通配符接受，只能输出，不能修改
        System.out.println("我的年龄是"+ c.getAge());
    }

    public static  void fun(ChildrenG<? super String> c){
        System.out.println(c.getAge());
    }

    //泛型方法

    /**
     *
     * @param array
     * @param i
     * @param t
     * @param <T>
     * @return
     */
    public static  <T> T[] func(T[] array, int i, int t){
        T temp = array[i];
        array[i] = array[t];
        array[t] = temp;
        return  array;
    }




}

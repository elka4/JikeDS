package _JAVA;

import org.junit.Test;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.BaseStream;



public class arrayToList {


    @Test
    public void test01(){
        int[] array = { 1, 2, 3 };
        ArrayList<Integer> list = new ArrayList<Integer>(array.length);
        for (int i = 0; i < array.length; i++)
            list.add(Integer.valueOf(array[i]));
        System.out.println(list);
    }


    @Test
    public void test02(){
//        In Java 8 with stream:

        int[] ints = {1, 2, 3};
        List<Integer> list = new ArrayList<Integer>();
        Collections.addAll(list, Arrays.stream(ints).boxed().toArray(Integer[]::new));
        System.out.println(list);
    }

    @Test
    public void test03(){
        int[] ints = {1, 2, 3};
        List<Integer> list =  Arrays.stream(ints).boxed().collect(Collectors.toList());
        System.out.println(list);
    }

    //  http://blog.csdn.net/chenleixing/article/details/43775127
/*  Array.asList:数组转list时你一定要知道的“陷阱”！
          最近开发中，业务上处理，经常用到asList方法，这让我不经想起了它的很多容易让人犯错的地方或者误解的地方，所以就想抽出时间来，整理一下，和大家分享出来，深夜了，话不多说，主要以代码为主，简易的代码，你一看就知道了!
大家都知道这个方法是将数组转成list，是JDK中java.util包中Arrays类的静态方法。大家使用时一定要注意（请看代码和注释，一看就明了了）：
 */
    @Test
    public void test04(){
        String s[]={"aa","bb","cc"};
        List<String> sList=Arrays.asList(s);
        for(String str:sList){//能遍历出各个元素
            System.out.println(str);
        }
        System.out.println(sList.size());//为3

        System.out.println("- - - - - - - - - - -");

        int i[]={11,22,33};
        List intList=Arrays.asList(i);  //intList中就有一个Integer数组类型的对象，整个数组作为一个元素存进去的
        for(Object o:intList){//就一个元素
            System.out.println(o.toString());
            System.out.println(((int[])o)[0]);
            System.out.println(((int[])o)[1]);
            System.out.println(((int[])o)[2]);
        }

        System.out.println("- - - - - - - - - - -");

        Integer ob[]={11,22,33};
        List<Integer> objList=Arrays.asList(ob);  //数组里的每一个元素都是作为list中的一个元素
        for(int a:objList){
            System.out.println(a);
        }

        System.out.println("- - - - - - - - - - -");

//objList.remove(0);//asList()返回的是arrays中私有的终极ArrayList类型，它有set,get，contains方法，但没有增加和删除元素的方法，所以大小固定,会报错
//objList.add(0);//由于asList返回的list的实现类中无add方法，所以会报错

    }
/*
aa
bb
cc
3
- - - - - - - - - - -
[I@3339ad8e
- - - - - - - - - - -
11
22
33
- - - - - - - - - - -
 */


    @Test
    public void test05(){
        int[] ints = {1, 2, 3};
        Integer[] ob = {1, 2, 3};
        //如果想根据数组得到一个新的正常的list,当然可可以循环一个一个添加，也可以才有以下2个种方法：
        ArrayList<Integer> copyArrays=new ArrayList<Integer>(Arrays.asList(ob));//这样就                                                           是得到一个新的list，可对其进行add,remove了
        copyArrays.add(222);//正常，不会报错

        Collections.addAll(new ArrayList<Integer>(5), ob);//或者新建一个空的list,把要转换的                                                                   数组用Collections.addAll添加进去

    }

    @Test
    public void test06(){
        /*
        如果你想直接根据基本类型的数组如int[],long[]直接用asList转成list,那么我们可以选择用apache commons-lang工具包里的数组工具类ArrayUtils类的toObject()方法，非常方便，如下：
         */
        //Arrays.asList(ArrayUtils.toObject(i));//上边的代码：int i[]={11,22,33};，达到了我们想要的效果

    }

        @Test
    public void test07(){
        int i[]={11,22,33};
        List intList=Arrays.asList(i); //这个方法把array转作Object，存入list. List<int[]>
            System.out.println(((int[])intList.get(0))[0]);
//        Iterator iterator =
            Iterator itr = intList.iterator();
            while(itr.hasNext()){
                System.out.println(itr.next());
            }
        System.out.println(intList);
    }
    /*
    [I@3339ad8e
    [[I@3339ad8e]
     */

    @Test
    public void test08(){
        int[] array = {1, 2};
        List<int[]> listOfArrays = Arrays.asList(array);
        System.out.println(listOfArrays);
        System.out.println(listOfArrays.contains(1));
    }

    @Test
    public void test09(){
        //如果是对象数组，例如将String数组转为list：
        String[] arr = new String[]{"a", "b"};
        List<String> list = Arrays.asList(arr);
        System.out.println(list);

    }

    @Test
    public void test10(){
//        如果是原型数据，例如将int数组转为list:
        int[] arr = new int[]{1, 2, 3};
//        List<Integer> list = IntStream.of(in.readIntArray()).boxed().collect(Collectors.toList());
    }

    @Test
    public void test11(){
/*        list转为数组
        如果是要转为对象数组，例如将String的List转为String的数组：*/
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");

        Object[] arr = list.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


    @Test
    public void test12(){
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        int[] arr = list.stream().mapToInt(i -> 2 * i).toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void test13(){
        int[] arr = new int[]{1, 2, 3};

        List<Integer> intList = Arrays.stream(arr).mapToObj(i->i).collect(Collectors.toList());
        System.out.println(intList);

    }

    @Test
    public void test14(){
//        最终，List转换为Array可以这样处理：

        ArrayList<String> list=new ArrayList<String>();

        list.add("aa");
        list.add("bb");
        list.add("cc");

        String[] strings = new String[list.size()];

        list.toArray(strings);

        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }

        System.out.println("-----------------");
//        反过来，如果要将数组转成List怎么办呢？如下：

        String[] s = {"a","b","c"};
        List list2 = java.util.Arrays.asList(s);
        System.out.println(list2);
    }

}

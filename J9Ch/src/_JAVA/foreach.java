package _JAVA;

import org.junit.Test;
import java.util.*;

public class foreach {
        @Test
        public void test01() {
            List<String> list = new ArrayList<String>();
            list.add("1");
            list.add("2");

            for (String temp : list) {
                if ("1".equals(temp)) {
                    list.remove(temp);
                }
            }
            System.out.println(list);
        }

///////////////////////////////////////////////////////////////////////////////////

    @Test
    public void test02() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        for (String temp : list) {
            if ("3".equals(temp)) {
                list.remove(temp);
            }
        }
        System.out.println(list);
    }
    /*
    java.util.ConcurrentModificationException
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901)
	at java.util.ArrayList$Itr.next(ArrayList.java:851)
	at _JAVA.foreach.test02(foreach.java:30)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)

     */

///////////////////////////////////////////////////////////////////////////////////
    @Test
    public void test03() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        for (String temp : list) {
            if ("3".equals(temp)) {
                temp = temp + "ss";
//                list.remove(temp);
            }
        }
        System.out.println(list);
    }


///////////////////////////////////////////////////////////////////////////////////

    class Obj{
        int id;
        String name;
        Obj(int id, String name){
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Test
    public void test04() {
        List<Obj> list = new ArrayList<Obj>();
        list.add(new Obj(1, "a"));
        list.add(new Obj(2, "b"));
        list.add(new Obj(3, "c"));
        list.add(new Obj(4, "d"));
        list.add(new Obj(5, "e"));

        for (Obj obj : list) {
            if (obj.id == 3) {
//                temp = temp + "ss";
//                obj.name = "dddd";
//                list.remove(obj);
                list.add(new Obj(6, "six"));
            }
        }
        System.out.println(list);
    }
    /*
    java.util.ConcurrentModificationException
     */
///////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////
}
/*
//       https://zhuanlan.zhihu.com/p/25744271
总结一下：
1）在使用For-Each快速遍历时，ArrayList内部创建了一个内部迭代器iterator，使用的是hasNext和next()方法来判断和取下一个元素。

2）ArrayList里还保存了一个变量modCount，用来记录List修改的次数，而iterator保存了一个expectedModCount来表示期望的修改次数，在每个操作前都会判断两者值是否一样，不一样则会抛出异常；

3）在foreach循环中调用remove()方法后，会走到fastRemove()方法，该方法不是iterator中的方法，而是ArrayList中的方法，在该方法中modCount++; 而iterator中的expectedModCount却并没有改变；

4）再次遍历时，会先调用内部类iteator中的hasNext(),再调用next(),在调用next()方法时，会对modCount和expectedModCount进行比较，此时两者不一致，就抛出了ConcurrentModificationException异常。

而为什么只有在删除倒数第二个元素时程序没有报错呢？

因为在删除倒数第二个位置的元素后，开始遍历最后一个元素时，先会走到内部类iterator的hasNext()方法时，里面返回的是 return cursor != size; 此时cursor是原size()-1,而由于已经删除了一个元素，该方法内的size也是原size()-1,故 return cursor != size;会返回false,直接退出for循环，程序便不会报错。


最后，通过源代码的判断，要在循环中删除元素，最好的方式还是直接拿到ArrayList对象下的迭代器 list.iterator()，通过源码可以看到，该方法也就是直接把内部的迭代器返回出来
  public Iterator<E> iterator() {
        return new Itr();
    }

而该迭代器正是在For-Each快速遍历中使用的迭代器Itr。
 */

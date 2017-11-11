package _JAVA;

import org.junit.Test;

public class Builder_Buffer {
    //https://www.javatpoint.com/difference-between-stringbuffer-and-stringbuilder
    /*
    Difference between StringBuffer and StringBuilder

There are many differences between StringBuffer and StringBuilder. A list of differences between StringBuffer and StringBuilder are given below:

No.	StringBuffer	StringBuilder

1)	StringBuffer is synchronized i.e. thread safe. It means two threads can't call the methods of StringBuffer simultaneously.

    StringBuilder is non-synchronized i.e. not thread safe. It means two threads can call the methods of StringBuilder simultaneously.


2)	StringBuffer is less efficient than StringBuilder.
    StringBuilder is more efficient than StringBuffer.
     */



    //StringBuffer Example
    @Test
    public void BufferTest(){
        StringBuffer buffer=new StringBuffer("hello");
        buffer.append("java");
        System.out.println(buffer);
    }

    //StringBuilder Example
    @Test
    public void BuilderTest(){
        StringBuilder builder=new StringBuilder("hello");
        builder.append("java");
        System.out.println(builder);
    }

    //Performance Test of StringBuffer and StringBuilder
    //Let's see the code to check the performance of StringBuffer and StringBuilder classes.
    @Test
    public void ConcatTest(){
        long startTime = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer("Java");
        for (int i=0; i<20000000; i++){
            sb.append("Tpoint");
        }
        System.out.println("Time taken by StringBuffer: " + (System.currentTimeMillis() - startTime) + "ms");
        startTime = System.currentTimeMillis();
        StringBuilder sb2 = new StringBuilder("Java");
        for (int i=0; i<20000000; i++){
            sb2.append("Tpoint");
        }
        System.out.println("Time taken by StringBuilder: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    /*
    Time taken by StringBuffer: 975ms
    Time taken by StringBuilder: 521ms
     */
/////////////////////////////////////////////////////////////////////////////////////////////

    //http://www.geeksforgeeks.org/g-fact-27-string-vs-stringbuilder-vs-stringbuffer/


/////////////////////////////////////////////////////////////////////////////////////////////
    //分析  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //https://segmentfault.com/a/1190000002683782
/*
我们先要记住三者的特征：

String 字符串常量
StringBuffer 字符串变量（线程安全）
StringBuilder 字符串变量（非线程安全）

    String:         private final char value[];
    StringBuffer:   AbstractStringBuilder char[] value;
    StringBuilder:  AbstractStringBuilder char[] value;

查看 API 会发现，String、StringBuffer、StringBuilder 都实现了 CharSequence 接口，内部都是用一个char数组实现，虽然它们都与字符串相关，但是其处理机制不同。

String：是不可改变的量，也就是创建后就不能在修改了。
StringBuffer：是一个可变字符串序列，它与 String 一样，在内存中保存的都是一个有序的字符串序列（char 类型的数组），不同点是 StringBuffer 对象的值都是可变的。
StringBuilder：与 StringBuffer 类基本相同，都是可变字符换字符串序列，不同点是 StringBuffer 是线程安全的，StringBuilder 是线程不安全的。

使用场景
使用 String 类的场景：在字符串不经常变化的场景中可以使用 String 类，例如常量的声明、少量的变量运算。

使用 StringBuffer 类的场景：在频繁进行字符串运算（如拼接、替换、删除等），并且运行在多线程环境中，则可以考虑使用 StringBuffer，例如 XML 解析、HTTP 参数解析和封装。

使用 StringBuilder 类的场景：在频繁进行字符串运算（如拼接、替换、和删除等），并且运行在单线程的环境中，则可以考虑使用 StringBuilder，如 SQL 语句的拼装、JSON 封装等。




分析  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
在性能方面，由于 String 类的操作是产生新的 String 对象，而 StringBuilder 和 StringBuffer 只是一个字符数组的扩容而已，所以 String 类的操作要远慢于 StringBuffer 和 StringBuilder。

简要的说， String 类型和 StringBuffer 类型的主要性能区别其实在于 String 是不可变的对象, 因此在每次对 String 类型进行改变的时候其实都等同于生成了一个新的 String 对象，然后将指针指向新的 String 对象。所以经常改变内容的字符串最好不要用 String ，因为每次生成对象都会对系统性能产生影响，特别当内存中无引用对象多了以后， JVM 的 GC 就会开始工作，那速度是一定会相当慢的。

而如果是使用 StringBuffer 类则结果就不一样了，每次结果都会对 StringBuffer 对象本身进行操作，而不是生成新的对象，再改变对象引用。所以在一般情况下我们推荐使用 StringBuffer ，特别是字符串对象经常改变的情况下。

而在某些特别情况下， String 对象的字符串拼接其实是被 JVM 解释成了 StringBuffer 对象的拼接，所以这些时候 String 对象的速度并不会比 StringBuffer 对象慢，而特别是以下的字符串对象生成中， String 效率是远要比 StringBuffer 快的：

String S1 = “This is only a" + “ simple" + “ test";
StringBuffer Sb = new StringBuilder(“This is only a").append(“ simple").append(“ test");
你会很惊讶的发现，生成 String S1 对象的速度简直太快了，而这个时候 StringBuffer 居然速度上根本一点都不占优势。其实这是 JVM 的一个把戏，在 JVM 眼里，这个

String S1 = “This is only a" + “ simple" + “test";
其实就是：

String S1 = “This is only a simple test";
所以当然不需要太多的时间了。但大家这里要注意的是，如果你的字符串是来自另外的 String 对象的话，速度就没那么快了，譬如：

1 String S2 = "This is only a";
2 String S3 = "simple";
3 String S4 = "test";
4 String S1 = S2 +S3 + S4;
这时候 JVM 会规规矩矩的按照原来的方式去做。



当使用 String s1 = "xyz"; 这样的表达是创建字符串的时候（非new这种方式），程序首先会在这个 String 缓冲池中寻找相同值的对象，

equals() 是object的方法，默认情况下，它与== 一样，比较的地址。
但是当equal被重载之后，根据设计，equal 会比较对象的value。而这个是java希望有的功能。String 类就重写了这个方法
 */

@Test
    public void testEquals(){
     //输出结果相同
    String[] hellos = "Hello Hello".split(" " );
    System.out.println(""+hellos[0].hashCode());//69609650
    System.out.println(""+hellos[1].hashCode());//69609650
    //输出结果相同
    String a = new String("hello");
    String b = new String("hello");
    System.out.println(""+a.hashCode());//99162322
    System.out.println(""+b.hashCode());//99162322
}
/*
结论
String 类是final类，不可以继承。对String类型最好的重用方式是组合 而不是继承。
String 有length（）方法，数组有length属性

String s = new String("xyz"); 创建了几个字符串对象？
两个对象，一个静态存储区“xyz”, 一个用new创建在堆上的对象。
String 和 StringBuffer,String Builder区别？
在大部分情况下 StringBuffer > String

Java.lang.StringBuffer 是线程安全的可变字符序列。一个类似于 String 的字符串缓冲区，但不能修改。虽然在任意时间点上它都包含某种特定的字符序列，但通过某些方法调用可以改变该序列的长度和内容。在程序中可将字符串缓冲区安全地用于多线程。而且在必要时可以对这些方法进行同步，因此任意特定实例上的所有操作就好像是以串行顺序发生的，该顺序与所涉及的每个线程进行的方法调用顺序一致。

StringBuffer 上的主要操作是 append 和 insert 方法，可重载这些方法，以接受任意类型的数据。每个方法都能有效地将给定的数据转换成字符串，然后将该字符串的字符追加或插入到字符串缓冲区中。append 方法始终将这些字符添加到缓冲区的末端；而 insert 方法则在指定的点添加字符。

例如，如果 z 引用一个当前内容是 “start"的字符串缓冲区对象，则此方法调用 z.append("le") 会使字符串缓冲区包含 “startle"( 累加); 而 z.insert(4, "le") 将更改字符串缓冲区，使之包含 “starlet"。

在大部分情况下 StringBuilder > StringBuffer

java.lang.StringBuilder 一个可变的字符序列是 JAVA 5.0 新增的。此类提供一个与 StringBuffer 兼容的 API，但不保证同步，所以使用场景是单线程。该类被设计用作 StringBuffer 的一个简易替换，用在字符串缓冲区被单个线程使用的时候（这种情况很普遍）。如果可能，建议优先采用该类，因为在大多数实现中，它比 StringBuffer 要快。两者的使用方法基本相同。
 */
/////////////////////////////////////////////////////////////////////////////////////////////


    //http://www.dczou.com/viemall/113.html
/////////////////////////////////////////////////////////////////////////////////////////////
}

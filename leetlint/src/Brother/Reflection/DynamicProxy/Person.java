package Brother.Reflection.DynamicProxy;

/**
 * 被代理类
 * Created by tzh on 1/21/17.
 */
public class Person implements Subject{
    private  String name;
    public Person(String name) {
        this.name = name;
    }
    public void miai() {
        System.out.println(name + "正在相亲中.....");
    }
}

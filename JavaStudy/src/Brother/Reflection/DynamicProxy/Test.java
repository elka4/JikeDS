package Brother.Reflection.DynamicProxy;

import java.lang.reflect.Proxy;

/**
 * Created by tzh on 1/21/17.
 */
public class Test {
    public  static  void main(String[] args) {
        Person p = new Person("小白");
        DynamicProxy dynamicProxy = new DynamicProxy(p);
        //Proxy 提供用于创建动态代理类和实例的静态方法
        //它还是由这些方法创建的所有动态代理类的超类

        //动态生成代理对象（类加载器，被代理接口，invocationHandler）

        Subject s = (Subject)Proxy.newProxyInstance(p.getClass().getClassLoader(), p.getClass().getInterfaces(), dynamicProxy);
        s.miai();
    }


}

package com.java2novice.generics;
 
public class _04MyBoundedInterface {
 
    public static void main(String a[]){
         
        //Creating object of implementation class X called Y and
        //passing it to BoundExmp as a type parameter.
        BoundExmp<Y> bey = new BoundExmp<Y>(new Y());
        bey.doRunTest();
        //Creating object of implementation class X called Z and
        //passing it to BoundExmp as a type parameter.
        BoundExmp<Z> bez = new BoundExmp<Z>(new Z());
        bez.doRunTest();
        //If you uncomment below code it will throw compiler error
        //becasue we restricted to only of type X  implementation classes.
        //BoundExmp<String> bes = new BoundExmp<String>(new String());
        //bea.doRunTest();
    }
}
 
class BoundExmp<T extends X>{
     
    private T objRef;
     
    public BoundExmp(T obj){
        this.objRef = obj;
    }
     
    public void doRunTest(){
        this.objRef.printClass();
    }
}
 
interface X{
    public void printClass();
}
 
class Y implements X{
    public void printClass(){
        System.out.println("I am in class Y");
    }
}
 
class Z implements X{
    public void printClass(){
        System.out.println("I am in class Z");
    }
}

/*Program: How implement bounded types (implements an interface) with generics?

As of now we have seen examples for only one type parameter. What happens in case we want to access group of objects comes from same family, means implementing same interface? You can restrict the generics type parameter to a certain group of objects which implements same interface. You can achieve this my specifying extends <interface-name> at class definitions, look at the example, it gives you more comments to understand. You can also specify multiple interfaces at the definision. you can do this by specifying mulitple interfaces seperated by "&". You can also specify class which implements an interface and the interface together. For example: 

class MyClass<T extends TestClass & TestInterface> { 

Look at example for more understanding.

- See more at: http://www.java2novice.com/java-generics/implements-interface/#sthash.ooDNQyOu.dpuf*/
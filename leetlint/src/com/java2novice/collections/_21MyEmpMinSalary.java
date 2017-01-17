package com.java2novice.collections;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class _21MyEmpMinSalary {
 
    public static void main(String a[]){
         
        List<Empl_21> emps = new ArrayList<Empl_21>();
        emps.add(new Empl_21(10, "Raghu", 25000));
        emps.add(new Empl_21(120, "Krish", 45000));
        emps.add(new Empl_21(210, "John", 14000));
        emps.add(new Empl_21(150, "Kishore", 24000));
        Empl_21 minSal = Collections.min(emps);
        System.out.println("Empl_21oyee with min salary: "+minSal);
    }
}
 
class Empl_21 implements Comparable<Empl_21>{
     
    private int id;
    private String name;
    private Integer salary;
     
    public Empl_21(int id, String name, Integer sal){
        this.id = id;
        this.name = name;
        this.salary = sal;
    }
     
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getSalary() {
        return salary;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    @Override
    public int compareTo(Empl_21 emp) {
         
        return this.salary.compareTo(emp.getSalary());
    }
    public String toString(){
        return id+"  "+name+"   "+salary;
    }
}
/*Program: How to get min element of a list of user defined
 *  objects?
 

Description:
Collections.min() method returns the minimum element of the 
given collection, according to the natural ordering of its
 elements. All elements in the collection must implement the
  Comparable interface. Furthermore, all elements in the 
  collection must be mutually comparable 
  (that is, e1.compareTo(e2) must not throw a 
  ClassCastException for any elements e1 and e2 in the 
  collection). Below example shows how to get min salary 
  from the given list of user defined objects.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/min-element-comparable/#sthash.GTi6xYNT.dpuf*/
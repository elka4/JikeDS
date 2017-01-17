package com.java2novice.collections;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
 
public class _22MyMaxComparator {
 
    public static void main(String a[]){
         
        List<Empy_22> emps = new ArrayList<Empy_22>();
        emps.add(new Empy_22(10, "Raghu", 25000));
        emps.add(new Empy_22(120, "Krish", 45000));
        emps.add(new Empy_22(210, "John", 14000));
        emps.add(new Empy_22(150, "Kishore", 24000));
        Empy_22 maxSal = Collections.max(emps, new Empy_22Comp());
        System.out.println("Employee with max salary: "+maxSal);
    }
}
 
class Empy_22Comp implements Comparator<Empy_22>{
 
    @Override
    public int compare(Empy_22 e1, Empy_22 e2) {
        return e1.getSalary().compareTo(e2.getSalary());
    }
}
 
class Empy_22{
     
    private int id;
    private String name;
    private Integer salary;
     
    public Empy_22(int id, String name, Integer sal){
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
    public String toString(){
        return id+"  "+name+"   "+salary;
    }
}
/*Program: How to get max element of a list of user defined 
 * objects using Comparator?

Description:
Collections.max() method returns the maximum element of the 
given collection, 
according to the order induced by the specified comparator. 
All elements in the collection must be mutually comparable 
by the specified comparator (that is, comp.compare(e1, e2) 
must not throw a ClassCastException for any elements e1 and
 e2 in the collection). Below example shows how to get max 
 salary from the given list of user defined objects by using
  comparator object.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/max-element-comparator/#sthash.Hn0182Bk.dpuf*/

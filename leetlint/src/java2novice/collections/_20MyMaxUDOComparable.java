package java2novice.collections;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class _20MyMaxUDOComparable {
 
    public static void main(String a[]){
         
        List<Empl_20> emps = new ArrayList<Empl_20>();
        emps.add(new Empl_20(10, "Raghu", 25000));
        emps.add(new Empl_20(120, "Krish", 45000));
        emps.add(new Empl_20(210, "John", 14000));
        emps.add(new Empl_20(150, "Kishore", 24000));
        Empl_20 maxSal = Collections.max(emps);
        System.out.println("Empl_20oyee with max salary: "+maxSal);
    }
}
 
class Empl_20 implements Comparable<Empl_20>{
     
    private int id;
    private String name;
    private Integer salary;
     
    public Empl_20(int id, String name, Integer sal){
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
    public int compareTo(Empl_20 emp) {
         
        return this.salary.compareTo(emp.getSalary());
    }
    public String toString(){
        return id+"  "+name+"   "+salary;
    }
}
/*Program: How to get max element of a list of user defined 
 

Description:
Collections.max() method returns the maximum element of the 
given collection,
 according to the natural ordering of its elements. 
 All elements in the collection must implement the Comparable
  interface. Furthermore, all elements in the collection must
   be mutually comparable (that is, e1.compareTo(e2) must not
    throw a ClassCastException for any elements e1 and e2 in 
    the collection). Below example shows how to get max salary
     from the given list of user defined objects.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/max-element-comparable/#sthash.tezxlBTq.dpuf*/
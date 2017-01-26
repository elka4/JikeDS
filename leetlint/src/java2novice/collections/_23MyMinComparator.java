package java2novice.collections;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
 
public class _23MyMinComparator {
 
    public static void main(String a[]){
         
        List<Empy_23> emps = new ArrayList<Empy_23>();
        emps.add(new Empy_23(10, "Raghu", 25000));
        emps.add(new Empy_23(120, "Krish", 45000));
        emps.add(new Empy_23(210, "John", 14000));
        emps.add(new Empy_23(150, "Kishore", 24000));
        Empy_23 minSal = Collections.min(emps, new Empy_23Comp());
        System.out.println("Employee with min salary: "+minSal);
    }
}
 
class Empy_23Comp implements Comparator<Empy_23>{
 
    @Override
    public int compare(Empy_23 e1, Empy_23 e2) {
        return e1.getSalary().compareTo(e2.getSalary());
    }
}
 
class Empy_23{
     
    private int id;
    private String name;
    private Integer salary;
     
    public Empy_23(int id, String name, Integer sal){
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
 *  objects using Comparator?

Description:
Collections.min() method returns the minimum element of the 
given collection, 
according to the order induced by the specified comparator.
 All elements in the collection must be mutually comparable
  by the specified comparator (that is, comp.compare(e1, e2)
   must not throw a ClassCastException for any elements e1 
   and e2 in the collection). Below example shows how to get
    min salary from the given list of user defined objects by 
    using comparator object.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/min-element-comparator/#sthash.fT7BZbfq.dpuf*/

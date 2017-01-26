package java2novice.treemap;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.Map.Entry;
 /*Program: How to get first key element from TreeMap (Sorted Map)?

Description:
Below example shows how to get first key from TreeMap based on sorting. 
To get first element, you have to sort the TreeMap based on user defined
 objects by using comparator object. You can include you own custom 
 sorting logic with compare method. By passing comparator object to 
 the TreeMap, you can sort the keys based on the logic provided inside
  the compare method. Once the TreeMap keys are in sorting order, 
  you can call firstKey() method to get the first key, then you can get 
  the corresponding value object.

- See more at: http://www.java2novice.com/java-collections-and-util/treemap/first-element/#sthash.W3EjfcGT.dpuf*/
public class _12MyTreeMapFirstElement {
 
    public static void main(String a[]){
        //By using salary comparator (int comparison)
        TreeMap<Emp,String> trmap = new TreeMap<Emp, String>(new MySalaryCompr());
        trmap.put(new Emp("Ram",3000), "RAM");
        trmap.put(new Emp("John",6000), "JOHN");
        trmap.put(new Emp("Crish",2000), "CRISH");
        trmap.put(new Emp("Tom",2400), "TOM");
        Emp em = trmap.firstKey();
        System.out.println("Highest salary emp: "+em);
        Entry<Emp,String> ent = trmap.firstEntry();
        System.out.println("Entry set values: ");
        System.out.println(ent.getKey()+" ==> "+ent.getValue());
    }
}
 
class MySalaryCompr implements Comparator<Emp>{
 
    @Override
    public int compare(Emp e1, Emp e2) {
        if(e1.getSalary() < e2.getSalary()){
            return 1;
        } else {
            return -1;
        }
    }
}
 
class Emp{
     
    private String name;
    private int salary;
     
    public Emp(String n, int s){
        this.name = n;
        this.salary = s;
    }
     
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public String toString(){
        return "Name: "+this.name+"-- Salary: "+this.salary;
    }
}

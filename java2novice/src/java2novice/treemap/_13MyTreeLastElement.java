package java2novice.treemap;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.TreeMap;
 /*Program: How to get last key element from TreeMap (Sorted Map)?

Description:
Below example shows how to get last key from TreeMap based on sorting. 
To get last element, you have to sort the TreeMap based on user defined 
objects by using comparator object. You can include you own custom 
sorting logic with compare method. By passing comparator object to 
the TreeMap, you can sort the keys based on the logic provided inside 
the compare method. Once the TreeMap keys are in sorting order,
 you can call lastKey() method to get the last key, then you can 
 get the corresponding value object.

- See more at: http://www.java2novice.com/java-collections-and-util/treemap/last-element/#sthash.heZRloYt.dpuf*/
public class _13MyTreeLastElement {
 
    public static void main(String a[]){
        //By using salary comparator (int comparison)
        TreeMap<Emp_2,String> trmap = new TreeMap<Emp_2, String>(new MySalaryCompr_2());
        trmap.put(new Emp_2("Ram",3000), "RAM");
        trmap.put(new Emp_2("John",6000), "JOHN");
        trmap.put(new Emp_2("Crish",2000), "CRISH");
        trmap.put(new Emp_2("Tom",2400), "TOM");
        Emp_2 em = trmap.lastKey();
        System.out.println("Least salary Emp_2: "+em);
        Entry<Emp_2,String> ent = trmap.lastEntry();
        System.out.println("Entry set values: ");
        System.out.println(ent.getKey()+" ==> "+ent.getValue());
    }
}
 
class MySalaryCompr_2 implements Comparator<Emp_2>{
 
    @Override
    public int compare(Emp_2 e1, Emp_2 e2) {
        if(e1.getSalary() < e2.getSalary()){
            return 1;
        } else {
            return -1;
        }
    }
}
 
class Emp_2{
     
    private String name;
    private int salary;
     
    public Emp_2(String n, int s){
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

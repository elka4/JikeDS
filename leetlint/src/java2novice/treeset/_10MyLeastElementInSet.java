package java2novice.treeset;

import java.util.Comparator;
import java.util.TreeSet;
 
public class _10MyLeastElementInSet {
 
    public static void main(String a[]){
         
        TreeSet<Empl1> salComp = new TreeSet<Empl1>(new MySalCompr());
        salComp.add(new Empl1("Ram",3000));
        salComp.add(new Empl1("John",6000));
        salComp.add(new Empl1("Crish",2000));
        salComp.add(new Empl1("Tom",2400));
        System.out.println("Least salary emp: "+salComp.first());
    }
}
 
class MySalCompr implements Comparator<Empl1>{
 
    @Override
    public int compare(Empl1 e1, Empl1 e2) {
        if(e1.getSalary() > e2.getSalary()){
            return 1;
        } else {
            return -1;
        }
    }
}
 
class Empl1{
     
    private String name;
    private int salary;
     
    public Empl1(String n, int s){
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

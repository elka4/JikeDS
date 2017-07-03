package java2novice.treeset;

import java.util.Comparator;
import java.util.TreeSet;
 
public class _11MyHighSalEmp {
    public static void main(String a[]){
         
        TreeSet<Empl2> salComp = new TreeSet<Empl2>(new MySalCompr1());
        salComp.add(new Empl2("Ram",3000));
        salComp.add(new Empl2("John",6000));
        salComp.add(new Empl2("Crish",2000));
        salComp.add(new Empl2("Tom",2400));
        System.out.println("Highest salary emp: "+salComp.last());
    }
}
 
class MySalCompr1 implements Comparator<Empl2>{
 
    @Override
    public int compare(Empl2 e1, Empl2 e2) {
        if(e1.getSalary() > e2.getSalary()){
            return 1;
        } else {
            return -1;
        }
    }
}
 
class Empl2{
     
    private String name;
    private int salary;
     
    public Empl2(String n, int s){
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

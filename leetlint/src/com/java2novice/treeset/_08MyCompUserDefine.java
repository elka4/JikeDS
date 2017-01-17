package com.java2novice.treeset;

import java.util.Comparator;
import java.util.TreeSet;
 
public class _08MyCompUserDefine {
 
    public static void main(String a[]){
        //By using name comparator (String comparison)
        TreeSet<Empl_2> nameComp = new TreeSet<Empl_2>(new MyNameComp_2());
        nameComp.add(new Empl_2("Ram",3000));
        nameComp.add(new Empl_2("John",6000));
        nameComp.add(new Empl_2("Crish",2000));
        nameComp.add(new Empl_2("Tom",2400));
        for(Empl_2 e:nameComp){
            System.out.println(e);
        }
        System.out.println("===========================");
        //By using salary comparator (int comparison)
        TreeSet<Empl_2> salComp = new TreeSet<Empl_2>(new MySalaryComp_2());
        salComp.add(new Empl_2("Ram",3000));
        salComp.add(new Empl_2("John",6000));
        salComp.add(new Empl_2("Crish",2000));
        salComp.add(new Empl_2("Tom",2400));
        for(Empl_2 e:salComp){
            System.out.println(e);
        }
    }
}
 
class MyNameComp_2 implements Comparator<Empl_2>{
 
    @Override
    public int compare(Empl_2 e1, Empl_2 e2) {
        return e1.getName().compareTo(e2.getName());
    }
}  
 
class MySalaryComp_2 implements Comparator<Empl_2>{
 
    @Override
    public int compare(Empl_2 e1, Empl_2 e2) {
        if(e1.getSalary() > e2.getSalary()){
            return 1;
        } else {
            return -1;
        }
    }
}
 
class Empl_2{
     
    private String name;
    private int salary;
     
    public Empl_2(String n, int s){
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

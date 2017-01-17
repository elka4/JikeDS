package com.java2novice.hashtable;

import java.util.Hashtable;

public class _11MyHashtableUserKeys {
 
    public static void main(String a[]){
 
        Hashtable<Emp_2,String> tm = new Hashtable<Emp_2, String>();
        tm.put(new Emp_2(134,"Ram",3000), "RAM");
        tm.put(new Emp_2(235,"John",6000), "JOHN");
        tm.put(new Emp_2(876,"Crish",2000), "CRISH");
        tm.put(new Emp_2(512,"Tom",2400), "TOM");
        System.out.println("Fecthing value by creating new key:");
         
        Emp_2 e = new Emp_2(512,"Tom",2400);
        System.out.println(e+" ==> "+tm.get(e));
    }
}
 
class Emp_2{
     
    private String name;
    private int salary;
    private int id;
     
    public Emp_2(int id, String n, int s){
        this.id = id;
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
        return "Id: "+this.id+" -- Name: "+this.name+" -- Salary: "+this.salary;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public int getId() {
        return id;
    }
     
    @Override
    public int hashCode() {
        System.out.println("In hashcode");
        return this.getId();
    }
     
    @Override
    public boolean equals(Object obj) {
        Emp_2 e = null;
        if(obj instanceof Emp_2){
            e = (Emp_2) obj;
        }
        System.out.println("In equals");
        if(this.getId() == e.getId()){
            return true;
        } else {
            return false;
        }
         
    }
}

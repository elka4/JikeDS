package com.java2novice.hashtable;

import java.util.Hashtable;
/*Program: Hashtable implementation with equals and hashcode example.

Description:
Below example shows how to use user defined objects as keys. 
To avoid duplicates keys you have to implement equals and hashcode methods. 
In below example we are using Emp object as key, and it has implemented 
equals, and hashcode methods. Example also enters duplicate object as key,
 and you can see that Hashtable eleminates the duplicate keys.

- See more at: http://www.java2novice.com/java-collections-and-util/hashtable/equals-hashcode/#sthash.edc1Sms9.dpuf*/
public class _10MyHashtableUserKeys {
 
    public static void main(String a[]){
 
        Hashtable<Emp,String> tm = new Hashtable<Emp, String>();
        tm.put(new Emp(134,"Ram",3000), "RAM");
        tm.put(new Emp(235,"John",6000), "JOHN");
        tm.put(new Emp(876,"Crish",2000), "CRISH");
        tm.put(new Emp(512,"Tom",2400), "TOM");
        System.out.println("Fecthing value by creating new key:");
         
        Emp e = new Emp(512,"Tom",2400);
        System.out.println(e+" ==> "+tm.get(e));
    }
}
 
class Emp{
     
    private String name;
    private int salary;
    private int id;
     
    public Emp(int id, String n, int s){
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
        Emp e = null;
        if(obj instanceof Emp){
            e = (Emp) obj;
        }
        System.out.println("In equals");
        if(this.getId() == e.getId()){
            return true;
        } else {
            return false;
        }
         
    }
}

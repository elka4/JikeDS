package com.jikexueyuan.one;

/**
 * Created by Student on 12/28/16.
 */
public class Person {
    private String name;
    private int id;

    public Person(){

    }

    public Person(int id, String name) {
        super();
        this.name = name;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Person + [id: " + id + " name: " + name + "]";
    }

}

package com.java2novice.hashset;

import java.util.HashSet;

public class _08MyHashSetSearchObject {
 
    public static void main(String a[]){
         
        HashSet<Price_2> lhs = new HashSet<Price_2>();
        lhs.add(new Price_2("Banana", 20));
        lhs.add(new Price_2("Apple", 40));
        lhs.add(new Price_2("Orange", 30));
        for(Price_2 pr:lhs){
            System.out.println(pr);
        }
        Price_2 key = new Price_2("Banana", 20);
        System.out.println("Does set contains key? "+lhs.contains(key));
    }
}
 
class Price_2{
     
    private String item;
    private int Price_2;
     
    public Price_2(String itm, int pr){
        this.item = itm;
        this.Price_2 = pr;
    }
     
    public int hashCode(){
        System.out.println("In hashcode");
        int hashcode = 0;
        hashcode = Price_2*20;
        hashcode += item.hashCode();
        return hashcode;
    }
     
    public boolean equals(Object obj){
        System.out.println("In equals");
        if (obj instanceof Price_2) {
            Price_2 pp = (Price_2) obj;
            return (pp.item.equals(this.item) && pp.Price_2 == this.Price_2);
        } else {
            return false;
        }
    }
     
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public int getPrice_2() {
        return Price_2;
    }
    public void setPrice_2(int Price_2) {
        this.Price_2 = Price_2;
    }
     
    public String toString(){
        return "item: "+item+"  Price_2: "+Price_2;
    }
}

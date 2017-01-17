package com.java2novice.dp.abstractfactory;
 
public class AbstractFactoryTest {
 
    public static void main(String a[]){
        Button btn = GUIFactory.createButton();
        btn.paint();
    }
}

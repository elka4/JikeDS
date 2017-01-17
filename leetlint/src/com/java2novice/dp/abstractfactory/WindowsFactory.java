package com.java2novice.dp.abstractfactory;
 
public class WindowsFactory implements ButtonFactory {
 
    @Override
    public Button createButton() {
         
        /**
         * this method creates a button for windows
         * button
         */
        System.out.println("creating windows button...");
        return new WindowsButton();
    }
 
}

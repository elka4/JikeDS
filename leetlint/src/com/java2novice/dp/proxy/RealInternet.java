package com.java2novice.dp.proxy;
 
public class RealInternet implements Internet {
 
    @Override
    public void connectTo(String host) {
        System.out.println("Connecting to "+host);
    }
}

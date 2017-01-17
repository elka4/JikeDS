package com.java2novice.dp.adapter;
 
public class FurnanceControllerAdapter extends FurnanceController{
 
    /**
     * this method access temperature only in centigrade format
     * @param heatLevel
     */
    public void controlFurnance(int heatLevel){
        // convert temperature from centigrade to fahrenheit formate
        heatLevel = (heatLevel - 32)*5/9;
        changeFurnanceTemperature(heatLevel);
    }
}

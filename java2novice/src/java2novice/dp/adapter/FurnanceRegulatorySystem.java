package java2novice.dp.adapter;
 
public class FurnanceRegulatorySystem {
 
    public void regulateFurnanceTemperature(){
        /**
         * here some lines of code gives temperature in centigrade format
         */
        FurnanceControllerAdapter fca = new FurnanceControllerAdapter();
        fca.controlFurnance(300);
    }
}

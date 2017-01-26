package java2novice.dp.builder.pattern;
 
public class CamaraOnlineSeller {
 
    public static void main(String a[]){
        CamaraMaker cm = new CamaraMaker();
        System.out.println("Creating camara with optical zoom...");
        CamaraBuilder ozcb = new OpticalZoomCamaraBuilder();
        cm.setCamaraBuilder(ozcb);
        cm.constructCamara();
        Camara cam = cm.getCamara();
    }
}

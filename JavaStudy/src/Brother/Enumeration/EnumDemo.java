package Brother.Enumeration;

import org.junit.Test;

/**枚举和类是兄弟关系
 * Created by tzh on 1/21/17.
 */
public class EnumDemo {
    public static void main(String[] args) {
        Controller on = Controller.ON;
        System.out.println(on);
        Controller off = on.downAction();
        System.out.println(off);
    }

    @Test
    public void test01(){
        EnumController ecON = EnumController.ON;
        System.out.println(ecON);
        //自动重写了toString

    }

    @Test
    public void test02(){
        EnumController ecON = EnumController.ON;
        EnumController ecOFF = EnumController.OFF;
        System.out.println(ecON);
        System.out.println(ecON.name());
        System.out.println(ecON.ordinal());
        EnumController[] es = ecON.values();
        //遍历枚举
        for (int i = 0; i < es.length; i++) {
            System.out.println(es[i]);
        }
        //System.out.println(ecON.);

        EnumController ecOff = EnumController.valueOf("OFF");
        System.out.println(ecOff);
        System.out.println(ecOFF.ordinal());
        //自动重写了toString

    }

    @Test
    public void test03(){
           EnumGirl eg1 = EnumGirl.Girl1;
           //四个实例，所以调用四次无参构造方法
    }


    @Test
    public void test04(){
        System.out.println(EnumColor.RED.getColor());
    }

    @Test
    public void test05(){
        System.out.println(EnumCotroller2.ON.downAction());
    }

}

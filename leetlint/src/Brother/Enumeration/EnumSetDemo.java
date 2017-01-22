package Brother.Enumeration;

import org.junit.Test;

import java.util.EnumSet;

/**
 * Created by tzh on 1/21/17.
 */
public class EnumSetDemo {
    public static void main(String[] args) {

    }

    @Test
    public void test01(){
        //性能更好
        EnumSet<EnumController> set = EnumSet.allOf(EnumController.class);
        for (EnumController enumController : set ) {
            System.out.println(enumController);
        }
    }


}
